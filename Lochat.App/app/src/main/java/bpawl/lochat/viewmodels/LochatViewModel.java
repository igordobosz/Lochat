package bpawl.lochat.viewmodels;

import androidx.lifecycle.ViewModel;
import bpawl.lochat.viewmodels.utils.IViewModelInitListener;

public class LochatViewModel extends ViewModel {
    protected IViewModelInitListener _initListener;

    public void init() {
        _onIsInit();
    }

    public void setInitListener(IViewModelInitListener listener) {
        _initListener = listener;
    }

    protected void _onIsInit(){
        if(_initListener != null) {
            _initListener.onViewModelInit();
        }
    }
}