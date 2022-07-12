'use strict';

const fs = require('fs');
const path = require('path');
const Sequelize = require('sequelize');
const basename = path.basename(__filename);
const env = process.env.NODE_ENV || 'development';
const config = require(__dirname + '/../config/config.json')[env];
const db = {};

function applyRelationships(sequelize) {
    const { OfferPost, OfferPostTags, RequestPost, RequestPostTags } = sequelize.models;

    OfferPost.hasMany(OfferPostTags, { foreignKey: 'offerId' });
    OfferPostTags.belongsTo(OfferPost, { foreignKey: 'offerId' });

    RequestPost.hasMany(RequestPostTags, { foreignKey: 'requestId' });
    RequestPostTags.belongsTo(RequestPost, { foreignKey: 'requestId' })
}

let sequelize;
if (config.use_env_variable) {
  sequelize = new Sequelize(process.env[config.use_env_variable], config);
} else {
  sequelize = new Sequelize(config.database, config.user, config.password, {
    host: config.host,
    port: 3306,
    logging: console.log,
    maxConcurrentQueries: 100,
    dialect: 'mysql',
    dialectOptions: env === "development" ? undefined : {
      ssl:'Amazon RDS'
  },
    pool: { maxConnections: 5, maxIdleTime: 30},
    language: 'en'
  });
}

fs
  .readdirSync(__dirname)
  .filter(file => {
    return (file.indexOf('.') !== 0) && (file !== basename) && (file.slice(-3) === '.js');
  })
  .forEach(file => {
    const model = require(path.join(__dirname, file))(sequelize, Sequelize.DataTypes);
    db[model.name] = model;
  });

Object.keys(db).forEach(modelName => {
  if (db[modelName].associate) {
    db[modelName].associate(db);
  }
});

applyRelationships(sequelize);

db.sequelize = sequelize;
db.Sequelize = Sequelize;

module.exports = db;