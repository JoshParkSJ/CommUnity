package com.example.community.ui.leaderboard;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.community.R;
import com.example.community.classes.UserWithScore;
import com.example.community.classes.Utils;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class LeaderboardAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<UserWithScore> users;
    private LayoutInflater inflater;
    private final String TAG = "LEADERBOARD_ADAPTER";

    public LeaderboardAdapter(Context applicationContext, ArrayList<UserWithScore> userList) {
        this.context = applicationContext;
        this.users = userList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return this.users.size();
    }

    @Override
    public Object getItem(int i) {
        return this.users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.fragment_leaderboard_user, null);
        TextView number = (TextView) view.findViewById(R.id.item_number);
        TextView name = (TextView) view.findViewById(R.id.chat_name);
        TextView score = (TextView) view.findViewById(R.id.leaderboard_score);
        UserWithScore user = this.users.get(i);

        number.setText(String.valueOf(i + 1));
        String nameAndInitial = user.firstName + " " + user.lastName.substring(0, 1).toUpperCase() + ".";
        name.setText(nameAndInitial);
        score.setText(String.valueOf(user.score));

        ImageView avatar = (ImageView) view.findViewById(R.id.chat_avatar);
        if (!Objects.equals(user.profilePicture, "")) {
            Utils.setImageWhenLoaded(context, user.profilePicture, avatar);
        } else {
            avatar.setImageDrawable(Utils.GetDefaultAvatar(context));
        }
        return view;
    }



}