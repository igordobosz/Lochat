package bpawl.lochat.services;

import androidx.fragment.app.FragmentManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import bpawl.lochat.ui.LochatFragment;

public class FragmentNavigation implements IFragmentNavigation {
    private int _containerID;
    private FragmentManager _fragmentManager;
    private Map<String, LochatFragment> _availableFragments;
    private String _current;
    private Stack<String> _history;

    public FragmentNavigation(Collection<LochatFragment> fragments) {
        _availableFragments = new HashMap<String, LochatFragment>();
        for (LochatFragment fragment : fragments) {
            registerFragment(fragment);
        }
        _history = new Stack<String>();
    }

    @Override
    public void setContainer(int containerID) {
        _containerID = containerID;
    }

    @Override
    public void setFragmentManager(FragmentManager manager) {
        _fragmentManager = manager;
    }

    @Override
    public void registerFragment(LochatFragment fragment) {
        _availableFragments.put(fragment.getClass().getName().toLowerCase(), fragment);
    }

    @Override
    public void navigateToFragment(String fragmentName) {
        String normalisedName = fragmentName.toLowerCase();
        if (_availableFragments.containsKey(normalisedName)) {
            _commitFragmentNavigation(normalisedName);
            if (_current != null) {
                _history.push(_current);
            }
            _current = normalisedName;
        }
    }

    @Override
    public Boolean back() {
        if (_history.empty()) {
            return false;
        } else {
            String previous = _history.pop();
            _commitFragmentNavigation(previous);
            _current = previous;
            return true;
        }
    }

    private void _commitFragmentNavigation(String normalisedName) {
        _fragmentManager.beginTransaction()
                .replace(_containerID, _availableFragments.get(normalisedName))
                .commitNow();
    }
}