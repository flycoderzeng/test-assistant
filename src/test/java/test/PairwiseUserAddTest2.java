package test;

import cn.hutool.core.lang.Dict;
import cn.hutool.setting.yaml.YamlUtil;
import com.test.assistant.builder.BaseAutoCaseBuilder;
import com.test.assistant.context.UserTestContext;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static com.test.assistant.utils.PairwiseTestUtils.*;

@Slf4j
public class PairwiseUserAddTest2 {
    public Dict dictAddUser;

    @BeforeClass
    public void before() {
        dictAddUser = YamlUtil.loadByPath(getFilePathWithName("add_users2.yml"));
    }

    @Test(testName = "测试添加用户", dataProvider = "allAddUserRows")
    public void testAddUserRights2(Map<String, String> row) throws Exception {
        runGroup(row, dictAddUser, "添加用户", "/user/addUsers", """
                        {
                            "userInfo": {
                                    "userName":"${$.userInfo.userName}",
                                    "occupation":${$.userInfo.occupation}
                                },
                                "sex":"${$.sex}"
                            }
                        }
                        """, UserTestContext.getInstance());
    }

    @DataProvider(name = "allAddUserRows")
    public Object[][] getAllAddUserRows() throws Exception {
        return getApiProviderObjects("add_users2.yml");
    }

    public static void main(String[] args) throws Exception {
        generateTestGroups("add_users2.yml");
    }
}
