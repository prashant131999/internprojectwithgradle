package eccomapp.cache;

import eccomapp.entity.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestCache {
    static Cache cache;
    static UserEntity userEntity;
    @BeforeAll
    public static void setup()
    {
        cache=new Cache(10);
        UserEntity userEntity=new UserEntity();
        userEntity.setEmail("prashant@gmail.com");
        userEntity.setMobileNumber("9639402926");
        userEntity.setAddress("bangalore");
        userEntity.setDateOfBirth("12-01-1999");
        userEntity.setFname("prashant");
        userEntity.setLname("sahrawat");
    }
    @Test
    public void testCache()
    {
        cache.put("prashant",userEntity);
        cache.contains("prashant");
        cache.get("prashant");
        cache.delete("prashant");
    }

}
