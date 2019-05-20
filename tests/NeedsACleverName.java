
import domein.DTO.GebruikerDTO;
import domein.Gebruiker;
import domein.Taijitan;
import java.util.Collection;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author Jonah
 */
public class NeedsACleverName {

    private Taijitan t;

    private DatabaseMock mockdata;
    private Collection<Gebruiker> local;
    private Gebruiker user;

    public NeedsACleverName() {
    }

    @Before
    public void setup() {
        t = mock(Taijitan.class);
        mockdata = new DatabaseMock();
        local = mockdata.getusers();
        user = mockdata.getuser();
    }

    @Test
    public void cleverName() {
        doAnswer((Answer) (InvocationOnMock iom) -> {
            Mock mock = (Mock) iom.getMock();
            return null;
        }).when(t).initUsers();
        verify(t, times(1)).initUsers();
    }

    @Test
    public void retrieveUser() {
        when(t.getUser(any())).thenReturn(user);
        Assert.assertEquals(user, t.getUser(any()));
    }
    
    @Test
    public void removeUser(){
        t.removeUser(user);
        Assert.assertTrue(local.isEmpty());
    }
    
    @Test
    public void updateUser(){
        GebruikerDTO dto = new GebruikerDTO();
        dto.setGebruikersnaam("unit.test");
        dto.setNaam("new.name");
        t.updateUser(dto, user);
        Assert.assertEquals("new.name", user.getNaam());
    }
}
