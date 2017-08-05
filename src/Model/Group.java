package Model;

import java.util.ArrayList;

/**
 * 分组类
 */
public class Group {

    /**
     * 存储该分组中的用户名
     */
    public ArrayList<String> member;

    /**
     * 分组的名称
     */
    public String groupName;

    public ArrayList<String> getMember() {
        return member;
    }

    public void setMember(ArrayList<String> member) {
        this.member = member;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 空构造方法
     */
    public Group() {
        member = new ArrayList<String>();
    }

    /**
     * 带参构造方法
     *
     * @param groupName 分组的名称
     */
    public Group(String groupName) {
        this();
        this.groupName = groupName;
    }

}
