package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import androidx.lifecycle.ViewModel;

public class SidebarViewModel extends ViewModel {
    public boolean isHomeActive;
    public boolean isLaundryActive;

    public boolean isHomeActive() {
        return isHomeActive;
    }

    public void setHomeActive(boolean homeActive) {
        isHomeActive = homeActive;
    }

    public boolean isLaundryActive() {
        return isLaundryActive;
    }

    public void setLaundryActive(boolean laundryActive) {
        isLaundryActive = laundryActive;
    }

    public void reset() {
        this.isHomeActive = false;
        this.isLaundryActive = false;
    }
}
