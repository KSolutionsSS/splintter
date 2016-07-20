package com.ksss.splintter.features.group.domain;

import android.support.annotation.NonNull;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * // TODO: 7/17/16 Add auto-value!!
 * Created by Nahuel Barrios on 7/17/16.
 */
public class Group extends RealmObject {

    private String name;

    private RealmList<Member> members;

    public Group() {
    }

    public Group(@NonNull String name) {
        this.name = name;
        this.members = new RealmList<>();
    }

    public String getName() {
        return name;
    }

    @NonNull
    public List<Member> getMembers() {
        return members;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", members=" + members +
                '}';
    }
}
