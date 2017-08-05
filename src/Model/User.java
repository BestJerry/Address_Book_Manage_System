package Model;

import java.util.ArrayList;

/**
 * �û���
 */
public class User {

    /**
     * ����
     */
    private String name;

    /**
     * �绰
     */
    public String call;

    /**
     * �ֻ�
     */
    public String phone;

    /**
     * ��ʱͨѶ���߼�����
     */
    public String qq;

    /**
     * ����
     */
    public String mail;

    /**
     * ������ҳ
     */
    public String personalPage;

    /**
     * ����
     */
    public String birthday;

    /**
     * ������λ
     */
    public String workPlace;

    /**
     * ��ͥסַ
     */
    public String location;

    /**
     * �ʱ�
     */
    public String postCode;

    /**
     * ��ע
     */
    public String remark;

    /**
     * �����ķ���
     */
    public String group;

    public ArrayList<String> PinyinName = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        PinyinName = new ArrayList<String>();
        String temp;
        for (int i = 0; i < name.length(); i++) {
            temp = (new Hanyu()).getCharacterPinYin(name.charAt(i));
            if (temp != null) {
                PinyinName.add(temp);
            } else {
                PinyinName.add(new String("" + name.charAt(i)));
            }
        }
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPersonalPage() {
        return personalPage;
    }

    public void setPersonalPage(String personalPage) {
        this.personalPage = personalPage;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * �չ��췽��
     */
    public User() {
    }

    /**
     * ���ι��췽��
     *
     * @param name         ����
     * @param call         �绰
     * @param phone        �ֻ�
     * @param qq           ��ʱͨ�Ź��߼�����
     * @param mail         ����
     * @param personalPage ������ҳ
     * @param birthday     ����
     * @param workPlace    �����ص�
     * @param location     ��ͥסַ
     * @param postCode     �ʱ�
     * @param remark       ��ע
     * @param group        �����ķ���
     */
    public User(String name, String call, String phone, String qq, String mail,
                String personalPage, String birthday, String workPlace, String location,
                String postCode, String remark, String group) {

        setName(name);
        this.call = call;
        this.phone = phone;
        this.qq = qq;
        this.mail = mail;
        this.personalPage = personalPage;
        this.birthday = birthday;
        this.workPlace = workPlace;
        this.location = location;
        this.postCode = postCode;
        this.remark = remark;
        this.group = group;

    }

}
