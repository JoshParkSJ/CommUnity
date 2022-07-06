package com.example.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.community.classes.Chat;
import com.example.community.classes.UserWithScore;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Chat> chats;
    private LayoutInflater inflater;

    public ChatAdapter(Context applicationContext, ArrayList<Chat> chatList) {
        this.context = applicationContext;
        this.chats = chatList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return this.chats.size();
    }

    @Override
    public Object getItem(int i) {
        return this.chats.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.fragment_chat_item, null);
        Chat chat = this.chats.get(i);

        TextView chatName = (TextView) view.findViewById(R.id.chat_name);
        chatName.setText(chat.name);


        return view;
    }
}