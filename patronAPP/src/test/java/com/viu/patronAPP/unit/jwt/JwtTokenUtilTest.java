import com.viu.patronAPP.config.token.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JwtTokenUtilTest {

    private JwtTokenUtil jwtTokenUtil;

    private static final String SECRET = "9sJ8HZ2+o9jI8H4pzOJlTG/qWj1YcLXt3OaWzVZvZlw=";  // 256 bits
    private static final String USERNAME = "testuser";

    private UserDetails mockUserDetails;

    @BeforeEach
    void setUp() {
        jwtTokenUtil = new JwtTokenUtil();

        // Using ReflectionTestUtils to inject the private field
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", SECRET);

        // Mock UserDetails
        mockUserDetails = Mockito.mock(UserDetails.class);
        when(mockUserDetails.getUsername()).thenReturn(USERNAME);
    }

    @Test
    void testGenerateToken() {
        String token = jwtTokenUtil.generateToken(mockUserDetails);

        assertNotNull(token, "Token should not be null");
        String username = jwtTokenUtil.extractUsername(token);
        assertEquals(USERNAME, username, "Extracted username should match the mock user");
    }

    @Test
    void testValidateToken_ValidToken() {
        String token = jwtTokenUtil.generateToken(mockUserDetails);
        boolean isValid = jwtTokenUtil.validateToken(token, mockUserDetails);

        assertTrue(isValid, "Token should be valid for the user");
    }

    @Test
    void testValidateToken_InvalidUsername() {
        String token = jwtTokenUtil.generateToken(mockUserDetails);

        UserDetails otherUser = Mockito.mock(UserDetails.class);
        when(otherUser.getUsername()).thenReturn("otheruser");

        boolean isValid = jwtTokenUtil.validateToken(token, otherUser);
        assertFalse(isValid, "Token should be invalid for a different user");
    }


    private String generateExpiredToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Key key = Keys.hmacShaKeyFor(SECRET.getBytes());  // Ensuring that key is decoded correctly

        // Expired token creation
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() - 100000);  // 100000 ms in the past
        Date issuedAt = new Date(now.getTime() - 200000);  // 200000 ms in the past

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Test
    void testExtractClaim() {
        String token = jwtTokenUtil.generateToken(mockUserDetails);

        String extractedClaim = jwtTokenUtil.extractClaim(token, Claims::getSubject);
        assertEquals(USERNAME, extractedClaim, "Extracted claim should match the username");
    }
}
