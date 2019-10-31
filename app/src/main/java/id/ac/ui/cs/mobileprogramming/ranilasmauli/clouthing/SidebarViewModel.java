package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import androidx.lifecycle.ViewModel;

public class SidebarViewModel extends ViewModel {
    public boolean isHomeActive;
    public boolean isLogActive;

    public boolean isHomeActive() {
        return isHomeActive;
    }

    public void setHomeActive(boolean homeActive) {
        isHomeActive = homeActive;
    }

    public boolean isLogActive() {
        return isLogActive;
    }

    public void setLogActive(boolean logActive) {
        isLogActive = logActive;
    }

    public void reset() {
        this.isHomeActive = false;
        this.isLogActive = false;
    }
}
