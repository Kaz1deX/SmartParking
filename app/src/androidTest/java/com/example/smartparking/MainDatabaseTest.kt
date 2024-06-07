import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.smartparking.data.room.dao.CarDao
import com.example.smartparking.data.room.dao.FavouriteDao
import com.example.smartparking.data.room.database.MainDatabase
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainDatabaseTest {

    private lateinit var database: MainDatabase
    private lateinit var carDao: CarDao
    private lateinit var favouriteDao: FavouriteDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, MainDatabase::class.java).build()
        carDao = database.carDao()
        favouriteDao = database.favouriteDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testDatabaseCreation() {
        assertNotNull(database)
    }

    @Test
    fun testCarDao() {
        assertNotNull(carDao)
    }

    @Test
    fun testFavouriteDao() {
        assertNotNull(favouriteDao)
    }
}