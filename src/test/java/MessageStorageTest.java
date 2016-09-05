import com.github.andx2.dinnernearserv.pojo.MessageStub;
import com.github.andx2.dinnernearserv.storage.MessageStorage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static com.github.andx2.dinnernearserv.util.SystemIO.sop;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by savos on 04.09.2016.
 */
public class MessageStorageTest {

    public static final String TAG = "MessageStorageTest: ";
    private static MessageStorage storage;

    @BeforeClass
    public static void initMessageStorage() {
        sop(TAG + "test BeforeClass");
        boolean thrown = false;
        try {
            storage = MessageStorage.getInstance(MessageStorage.H2);
        } catch (SQLException e) {
            sop(TAG + "BeforeClass: SQLException");
            thrown = true;
        }
        assertTrue(!thrown);
    }

    @AfterClass
    public static void closeConnection() {
        sop(TAG + "test AfterClass");
        boolean thrown = false;
        try {
            storage.closeConnection();
        } catch (SQLException e) {
            sop(TAG + "AfterClass: SQLException");
            e.printStackTrace();
            thrown = true;
        }
        assertTrue(!thrown);
    }

    @Test
    public void create() {
        sop(TAG + "test create");
        boolean thrown = false;
        int countAddedObjects = 0;
        try {
            countAddedObjects = countAddedObjects + storage.add(new MessageStub(1L, "Name1", "Desc1"));
            countAddedObjects = countAddedObjects + storage.add(new MessageStub(1L, "Name2", "Desc2"));
        } catch (SQLException e) {
            sop(TAG + "create: SQLException");
            e.printStackTrace();
            thrown = true;
        }
        assertEquals(countAddedObjects, 2);
    }

    @Test
    public void findById() {
        sop(TAG + "test findById");
        MessageStub messageStub = null;
        try {
            messageStub = storage.findById(2L);
        } catch (SQLException e) {
            sop(TAG + "findById: SQLException");
            e.printStackTrace();
        }
        assertTrue(messageStub != null);
        assertTrue(messageStub.getName() != null && messageStub.getName().length() > 0);
    }

    @Test
    public void findAll() {
        sop(TAG + "test findAll");
        boolean thrown = false;
        try {
            List<MessageStub> list = storage.findAll();
            assertTrue(list.size() > 0);
        } catch (SQLException e) {
            sop(TAG + "findAll: SQLException");
            thrown = true;
            e.printStackTrace();
        }
        assertTrue(!thrown);
    }

    @Test
    public void update() {
        sop(TAG + "test update");
        boolean thrown = false;
        try {
            List<MessageStub> list = storage.findAll();
            list.get(0).setName("anotherName");
            storage.update(list.get(0));
            Long id = list.get(0).getId();
            assertEquals(storage.findById(id).getName(), "anotherName");
        } catch (SQLException e) {
            sop(TAG + "update: SQLException");
            thrown = true;
            e.printStackTrace();
        }
        assertTrue(!thrown);

    }

    @Test
    public void delete() {
        sop(TAG + "test delete");
        boolean thrown = false;
        try {
            List<MessageStub> list = storage.findAll();
            int i = storage.delete(list.get(0).getId());
            assertEquals(i, 1);
            assertEquals(storage.findAll().size(), list.size() - 1);
        } catch (SQLException e) {
            sop(TAG + "update: SQLException");
            thrown = true;
            e.printStackTrace();
        }
        assertTrue(!thrown);

    }
}
