package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;;

/**
 * ���ݿ�����
 */
public class Data {

    /**
     * �洢���е��û�����
     */
    public ArrayList<User> userList;

    /**
     * �洢�����еķ������
     */
    public ArrayList<Group> groupList;

    /**
     * �ļ�����
     */
    public File file;

    /**
     * �չ��췽��
     */
    public Data() {
        userList = new ArrayList<User>();
        groupList = new ArrayList<Group>();
    }

    /**
     * ����ͨѶ¼ʱ�Ĺ��췽��
     *
     * @param file ������ļ�����
     */
    public Data(File file) {
        this();

        this.file = file;

        userList = new ArrayList<User>();
        groupList = new ArrayList<Group>();

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            ArrayList<String> temp = new ArrayList<String>();

            String line;

            while ((line = reader.readLine()) != null) temp.add(line);

			/*
             * ��ȡ�������
			 */
            for (int x = 0; x < temp.size(); x++) {

                String[] item = temp.get(x).split(","); // �Զ���Ϊ�������ȡ��������

                if (item[0].equals("") == true) {
                    groupList.add(new Group(item[11]));
                }
            }

			/*
             * ��ȡ��ϵ�˶���
			 */
            for (int x = 0; x < temp.size(); x++) {

                String[] item = temp.get(x).split(","); // �Զ���Ϊ�������ȡ��������

                if (item[0].equals("") == true) continue;

				/*
                 * �������ϵ�˴��ڷ���
				 */
                if (item[11].equals("null") == false) {

                    userList.add(new User(item[0], item[1], item[2], item[3], item[4], item[5], item[6], item[7], item[8],
                            item[9], item[10], item[11]));

                    for (int i = 0; i < groupList.size(); i++) {
                        if (groupList.get(i).groupName.equals(item[11])) {
                            groupList.get(i).member.add(item[0]);
                            break;
                        }
                    }
                } else { /*û�з���*/
                    userList.add(new User(item[0], item[1], item[2], item[3], item[4], item[5], item[6], item[7], item[8],
                            item[9], item[10], null));
                }

            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * �½�ͨѶ¼ʱ�Ĺ��췽��
     *
     * @param fileName Ҫ�½����ļ���
     */
    public Data(String fileName) {
        this();

        this.file = new File("AddressBook", fileName + ".csv");

    }



	/*
	 *
	 *
	 *
	 */


    /**
     * <b>�����µķ���</b>
     *
     * @param groupName �·��������
     */
    public void addGroup(String groupName) {

        groupList.add(new Group(groupName));

    }

    /**
     * <b>ɾ������</b>
     *
     * @param groupName Ҫɾ���ķ��������
     */
    public void deleteGroup(String groupName) {
        for (int i = 0; i < groupList.size(); i++) {

            if (groupList.get(i).groupName.equals(groupName)) {

				/* �����û��б����ڸ÷�����û��ķ���������Ϊ�� */
                for (int j = 0; j < userList.size(); j++) {
                    if (groupName.equals(userList.get(j).group)) {
                        userList.get(j).group = null;
                    }
                }

                groupList.remove(i);
                break;
            }

        }
    }



	/*
	 *
	 *
	 *
	 */


    /**
     * <b>������ϵ��</b><br>
     * �����ϵ��û�з������ԵĻ���������group��Ϊnull
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
    public void addUser(String name, String call, String phone, String qq, String mail, String personalPage,
                        String birthday, String workPlace, String location, String postCode, String remark, String group) {

        userList.add(new User(name, call, phone, qq, mail, personalPage, birthday, workPlace, location, postCode,
                remark, group));

		/* �������ϵ��ӵ�з���,���ڸ÷����������ϵ������ */
        if (group != null) {
            for (int i = 0; i < groupList.size(); i++) {
                if (groupList.get(i).groupName.equals(group)) {
                    groupList.get(i).member.add(name);
                    break;
                }
            }
        }

    }

    /**
     * <b>ɾ����ϵ��</b>
     *
     * @param user ��Ҫɾ������ϵ�˶���
     */
    public void deleteUser(User user) {

		/* �������ϵ�˴��ڷ��� */
        if (user.group != null) {
			/* ���Ҹ���ϵ�����ڵķ��鲢ɾ�������� */
            for (int i = 0; i < groupList.size(); i++) {
                if (groupList.get(i).groupName.equals(user.group)) {
                    groupList.get(i).member.remove(user.getName());
                    break;
                }
            }
        }

        userList.remove(user);

    }

    /**
     * <b>������ϵ��</b><br>
     * �����ϵ��û�з������ԵĻ���������group��Ϊnull
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
     * @param user         ��Ҫ���µ���ϵ�˶���
     */
    public void updataUser(String name, String call, String phone, String qq, String mail, String personalPage,
                           String birthday, String workPlace, String location, String postCode, String remark,
                           String group, User user) {

        /*deleteUser(user);
        addUser(name, call, phone, qq, mail, personalPage, birthday, workPlace, location, postCode, remark, group);*/
        for (User user1 : userList) {
            if (user1.getName().equals(user.getName())) {
                //user1.name = name;
                user1.setName(name);
                user1.call = call;
                user1.phone = phone;
                user1.qq = qq;
                user1.mail = mail;
                user1.personalPage = personalPage;
                user1.birthday = birthday;
                user1.workPlace = workPlace;
                user1.location = location;
                user1.postCode = postCode;
                user1.remark = remark;
                user1.group = group;
                break;
            }

        }


    }




	/*
	 *
	 *
	 */


    /**
     * <b>���沢����ͨѶ¼</b>
     */
    public void save() {

        try {

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

            for (int i = 0; i < userList.size(); i++) {
                writer.write(userList.get(i).getName() + "," + userList.get(i).call + "," + userList.get(i).phone + ","
                        + userList.get(i).qq + "," + userList.get(i).mail + "," + userList.get(i).personalPage + ","
                        + userList.get(i).birthday + "," + userList.get(i).workPlace + "," + userList.get(i).location
                        + "," + userList.get(i).postCode + "," + userList.get(i).remark + ",");

				/*�������ϵ��û�з��������������Ϊ"null"*/
                if (userList.get(i).group == null) {
                    writer.write("null" + "\n");
                } else {
                    writer.write(userList.get(i).group + "\n");
                }
            }


            for (int i = 0; i < groupList.size(); i++) {
                writer.write(",null,null,null,null,null,null,null,null,null,null," + groupList.get(i).groupName + "\n");
            }

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * <b>������ϵ��</b>
     *
     * @param words �����Ĺؼ���
     * @return ���Ͻ�����û�����ļ����б�
     */
    public ArrayList<User> search(String words) {

        ArrayList<User> result = new ArrayList<User>();
        String temp = "[\\S]{0,15}" + words + ".*";
        String temp2 = "[\\S]{0,15}" + words.toLowerCase() + ".*";
        String str;

        for (int i = 0; i < userList.size(); i++) {
        	
        	/*����������ʽ�����û��б�*/
            if (userList.get(i).getName().matches(temp) || userList.get(i).call.matches(temp) ||
                    userList.get(i).phone.matches(temp)) {
                result.add(userList.get(i));
            } else if(userList.get(i).getName().toLowerCase().matches(temp2) || userList.get(i).call.toLowerCase().matches(temp2) ||
                    userList.get(i).phone.toLowerCase().matches(temp2)) {
                result.add(userList.get(i));
            }
            
            /*���ݺ���ƴ��ƥ���û�����*/
            else {

                words = words.toLowerCase();

                ArrayList<String> t = userList.get(i).PinyinName;

                int index = 0; //words����ƥ�䵽���ַ��±�
                boolean flag = true;//�Ƿ�ƥ��ɹ�
                for (int j = 0; j < t.size() && index < words.length(); j++) {

                    str = t.get(j);
                    if (str.charAt(0) != words.charAt(index)) {
                        flag = false;
                        break;
                    }

                    for (int k = 0; k < str.length() && index < words.length(); k++) {
                        if (str.charAt(k) == words.charAt(index)) {
                            index++;
                        } else {
                            break;
                        }
                    }

                }

                if (flag && index == words.length()) {
                    result.add(userList.get(i));
                }
            }

        }

        return result;
    }


}