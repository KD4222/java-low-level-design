package repo;
import java.util.HashMap;

import models.*;

public class MemberRepo {
    private HashMap<String,Member> members= new HashMap<>();

    public Member getMember(String id){
        return members.get(id);
    }

    public boolean memberAlreadyExists(String id){
        return members.containsKey(id);
    }

    public void addMember(Member member){
        members.put(member.getMemberId(),member);
    }
}
