package net.ekcapaper.racingar.components.data;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.heroiclabs.nakama.Client;

import org.junit.Test;
import org.junit.runner.RunWith;

public class ThisApplicationTest {

    @Test
    public void onCreate() {
        Context appContext =InstrumentationRegistry.getInstrumentation().getTargetContext();
        Client client =((ThisApplication)appContext.getApplicationContext()).getGameAppOperator().getClient();
        assertNotNull(client);
    }
}