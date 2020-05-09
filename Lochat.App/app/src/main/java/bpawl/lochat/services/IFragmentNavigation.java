package bpawl.lochat.services;

import androidx.fragment.app.FragmentManager;

import bpawl.lochat.ui.LochatFragment;

public interface IFragmentNavigation {
    public void setContainer(int containerID);
    public void setFragmentManager(FragmentManager manager);
    public void registerFragment(LochatFragment fragment);
    public void navigateToFragment(String fragmentName);
    public Boolean back();
}
