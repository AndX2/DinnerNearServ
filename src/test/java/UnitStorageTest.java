import com.github.andx2.dinnernearserv.pojo.GroupUnits;
import com.github.andx2.dinnernearserv.pojo.Unit;
import com.github.andx2.dinnernearserv.storage.UnitStorage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.github.andx2.dinnernearserv.util.StringHelper.randStr;
import static com.github.andx2.dinnernearserv.util.SystemIO.sop;
import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by savos on 04.09.2016.
 */
public class UnitStorageTest {
    public static final String TAG = "UnitStorageTest: ";

    private static UnitStorage unitStorage;

    @BeforeClass
    public static void initTest() {
        sop(TAG + "test BeforeClass");
        boolean thrown = false;

        try {
            unitStorage = UnitStorage.getInstance(UnitStorage.H2);
        } catch (SQLException e) {
            sop(TAG + "test BeforeClass SQLException");
            e.printStackTrace();
            thrown = true;
        }

        assertTrue(!thrown && unitStorage != null);
    }

    @AfterClass
    public static void closeDb() {
        sop(TAG + "test AfterClass");
        try {
            unitStorage.closeConnection();
        } catch (SQLException e) {
            sop(TAG + "test AfterClass SQLException");
            e.printStackTrace();
        }
    }

    @Test
    public void create() {
        sop(TAG + "test create");
        List<Unit> list = new ArrayList<>();
        GroupUnits groupUnits = new GroupUnits();
        groupUnits.setId(6L);
        groupUnits.setName("GroupNAmeStub1");
        groupUnits.setDescription("GroupDescStub1");
        try {
            for (int i = 0; i < 5; i++) {
                Unit unit = new Unit();
                unit.setName(randStr());
                unit.setDescription(randStr());
                unit.setGroup(groupUnits);
                unitStorage.add(unit);
            }

            list = unitStorage.findAll();

        } catch (SQLException e) {
            sop(TAG + "test create SQLException");
            e.printStackTrace();
        }

        assertEquals(list.size(), 5);
    }

}
