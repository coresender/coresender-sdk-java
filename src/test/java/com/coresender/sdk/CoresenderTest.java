package com.coresender.sdk;

import com.coresender.sdk.Coresender.CoresenderBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CoresenderTest {

    @DisplayName("Setup sender with environment variables")
    @Test
    public void testApiSetupWithEnvironmentVariables() {
        CoresenderBuilder builder = mock(CoresenderBuilder.class);
        doReturn("id-123").when(builder).getEnvironmentVariable(Coresender.CORESENDER_SENDING_API_ID);
        doReturn("key-456").when(builder).getEnvironmentVariable(Coresender.CORESENDER_SENDING_API_KEY);
        when(builder.build()).thenCallRealMethod();
        builder.build();
    }

    @DisplayName("Setup sender with missing environment variables")
    @Test
    public void testApiSetupWithMissingEnvironmentVariables() {
        assertThrows(IllegalArgumentException.class, () -> Coresender.builder().build());
    }
}
