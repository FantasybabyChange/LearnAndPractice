package com.fantasybaby.convert.dozer;

/**
 * @author liuxi
 * @date2017年12月13日 18:22
 */
public class TestDozer {
    public static void main(String[] args) {
        UserDTO userDTO = new UserDTO();
        /*userDTO.setId(Long.parseLong("123"));
        userDTO.setUserName("用户名");*/
        UserDO userDO = new UserDO();
        userDO.setUserID("123");
        userDO.setUserName("用户名");
        DozerUtil.convertObject(userDO,userDTO);
        System.out.println(userDTO);
        UserDTO userDtONew = (UserDTO)DozerUtil.convertObject(userDO, UserDTO.class);
        System.out.println(userDtONew);
    }
}
