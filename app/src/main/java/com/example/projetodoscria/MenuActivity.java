package com.example.projetodoscria;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.projetodoscria.activity.CortadorVideoActivity;
import com.example.projetodoscria.fragment.FragmentPerfil;

import life.knowledge4.videotrimmer.utils.FileUtils;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;

    static int RESULT_LOAD_IMAGEM = 2;
    static int RESULT_LOAD_VIDEO = 3;

    public static String CAMINHO_VIDEO;

    Uri imagemSelecionada, videoSelecionado;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displayView(item.getItemId());

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, getString(R.string.permission_read_storage_rationale), REQUEST_STORAGE_READ_ACCESS_PERMISSION);

        getMenuInflater().inflate(R.menu.menu_propaganda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuEnviarPropaganda) {

            exibirDialogGaleria(this);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_VIDEO && resultCode == RESULT_OK && data != null) {
            videoSelecionado = data.getData();

            startActivity(new Intent(this, CortadorVideoActivity.class).putExtra(CAMINHO_VIDEO, FileUtils.getPath(this, videoSelecionado)));
        }

        if (requestCode == RESULT_LOAD_IMAGEM && resultCode == RESULT_OK && data != null) {
            imagemSelecionada = data.getData();

            startActivity(new Intent(this, CortadorVideoActivity.class).putExtra(CAMINHO_VIDEO, FileUtils.getPath(this, videoSelecionado)));
        }

    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.opcao_perfil:
                fragment = new FragmentPerfil();
                title = "Perfil";

                break;
        }

        iniciarFragment(fragment, title);
    }

    public void iniciarFragment(Fragment fragment, String title) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.frameLayout, fragment);
            fragmentTransaction.commit();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.permission_title_rationale));
            builder.setMessage(rationale);
            builder.setPositiveButton(getString(R.string.label_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(MenuActivity.this, new String[]{permission}, requestCode);
                }
            });
            builder.setNegativeButton(getString(R.string.label_cancel), null);
            builder.show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    public void exibirDialogGaleria(Activity activity) {
        Dialog dialog = new Dialog(activity);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_galeria);

        Button buttonImagemDialog = (Button) dialog.findViewById(R.id.buttonImagemDialog);
        Button buttonVideoDialog = (Button) dialog.findViewById(R.id.buttonVideoDialog);

        buttonImagemDialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, null).setType("image/*"), RESULT_LOAD_IMAGEM);
            }
        });

        buttonVideoDialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, null).setType("video/*"), RESULT_LOAD_VIDEO);
            }
        });

        dialog.show();
    }

}