package Model;

import java.util.ArrayList;

/**
 * ������
 */
public class Group {

    /**
     * �洢�÷����е��û���
     */
    public ArrayList<String> member;

    /**
     * ���������
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
     * �չ��췽��
     */
    public Group() {
        member = new ArrayList<String>();
    }

    /**
     * ���ι��췽��
     *
     * @param groupName ���������
     */
    public Group(String groupName) {
        this();
        this.groupName = groupName;
    }

}
