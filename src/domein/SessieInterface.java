package domein;

import java.time.LocalDateTime;
import java.util.List;

public interface SessieInterface {
    public LocalDateTime getSessieDatum();
    
    public List<LidSessie> getLedenlijst();
}
