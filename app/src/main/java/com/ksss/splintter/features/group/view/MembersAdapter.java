package com.ksss.splintter.features.group.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

import com.ksss.splintter.features.group.domain.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nahuel Barrios on 7/17/16.
 */
public class MembersAdapter extends ArrayAdapter<String> {

    private final List<Member> members;

    /* package */ MembersAdapter(Context context, List<Member> members) {
        super(context, android.R.layout.simple_dropdown_item_1line, parse(members));
        this.members = members;
    }

    private static List<String> parse(List<Member> members) {
        List<String> membersName = new ArrayList<>();

        for (Member eachMember : members) {
            membersName.add(eachMember.getName());
        }

        return membersName;
    }

    @Nullable
    /* package */ Member findByName(@NonNull String name) {
        Member result = null;
        for (Member eachMember : members) {
            if (eachMember.getName().equalsIgnoreCase(name)) {
                result = eachMember;
                break;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "MembersAdapter{" +
                "members=" + members +
                '}';
    }
}
