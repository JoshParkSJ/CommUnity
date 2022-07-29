const { getSuggestedRequests, getSuggestedOffers, deleteSuggestedRequest, deleteSuggestedOffer } = require("../../__mocks__/userController.js");

jest.mock("../../controllers/userController.js")

describe("Recommendation Controller", () => {
  it("triggers the mock for getting suggested request posts", () => {
    let result = getSuggestedRequests('peepeepoopoo');
    expect(result).toEqual([{postId: 'post1', score: 100}, { postId: 'post2', score: 50}]);
    expect(getSuggestedRequests).toHaveBeenCalledWith('peepeepoopoo');
    expect(getSuggestedRequests).toHaveBeenCalledTimes(1);
  });

  it("triggers the mock for getting suggested offer posts", () => {
    let result = getSuggestedOffers('peepeepoopoo');
    expect(result).toEqual([{postId: 'post1', score: 100}, { postId: 'post2', score: 50}]);
    expect(getSuggestedOffers).toHaveBeenCalledWith('peepeepoopoo');
    expect(getSuggestedOffers).toHaveBeenCalledTimes(1);
  });

  it("triggers the mock for deleting request posts", () => {
    let result = deleteSuggestedRequest();
    expect(result).toBe(true);
  });

  it("triggers the mock for deleting offer posts", () => {
    let result = deleteSuggestedOffer();
    expect(result).toBe(true);
  });
});