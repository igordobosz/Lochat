package bpawl.lochat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

import bpawl.lochat.api.IGoogleConnection;
import bpawl.lochat.model.User;
import bpawl.lochat.services.IUserManager;
import bpawl.lochat.services.utils.IRequestFailedListener;
import bpawl.lochat.services.utils.IRequestSuccessfulListener;
import bpawl.lochat.services.utils.IUserManagerInitListener;
import bpawl.lochat.ui.ChatMap;
import bpawl.lochat.ui.Profile;
import bpawl.lochat.ui.utils.ISigningFailedListener;

public class MainActivity extends LochatActivity implements IUserManagerInitListener, IRequestSuccessfulListener<User>, IRequestFailedListener {
    private String SERVER_CLIENT_ID = "360793568936-27c8qvgkqn0bbj8j4po9vrjil2rpn12b.apps.googleusercontent.com";
    private int RC_SIGN_IN = 975;

    private Toolbar _toolbar;
    private ISigningFailedListener _listener;
    private GoogleSignInClient _client;

    @Inject public IGoogleConnection googleConnection;
    @Inject public IUserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        _toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(_toolbar);

        _lochatApp.appComponent.inject(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(SERVER_CLIENT_ID)
                .build();
        _client = GoogleSignIn.getClient(this, gso);
        _silentSignIn();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                fragmentNavigation.navigateToFragment(Profile.class.getName());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            _handleSignInResult(task);
        }
    }

    public void setSigningFailedListener(ISigningFailedListener listener) {
        _listener = listener;
    }

    public void signIn() {
        Intent signInIntent = _client.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void hideToolbar() { _toolbar.setVisibility(View.GONE); }
    public void showToolbar() { _toolbar.setVisibility(View.VISIBLE); }

    private void _silentSignIn() {
        _client.silentSignIn().addOnCompleteListener(
            this,
            new OnCompleteListener<GoogleSignInAccount>() {
                @Override
                public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                    _handleSignInResult(task);
                }
            });
    }

    private void _handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            googleConnection.setToken(account.getIdToken());
            googleConnection.setEmail(account.getEmail());
            userManager.init(this);
        } catch (ApiException e) {
            Log.e(this.getClass().getSimpleName(), new Integer(e.getStatusCode()).toString());
            if(_listener != null) {
                _listener.onSignInFailed();
            }
        }
    }

    @Override
    public void onRequestFailed() {
        userManager.signIn(this, this);
    }

    @Override
    public void onRequestSuccessful(User result) {
        showToolbar();
        fragmentNavigation.navigateToFragment(ChatMap.class.getName());
    }

    @Override
    public void onUserManagerInit() {
        userManager.signIn(this, this);
    }
}
