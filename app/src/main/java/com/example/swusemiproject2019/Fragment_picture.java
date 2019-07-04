package com.example.swusemiproject2019;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.swusemiproject2019.database.Database;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment_picture extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private static final int REQ_TAKE_PHOTO = 2222;

    Button btnPic;
    ImageView imgPic;
    String mCurrentImageFilePath = null;
    Uri mProviderUri = null;
    Uri mPhotoUri = null;

    public Fragment_picture() {} // Default Constructor

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        imgPic = view.findViewById(R.id.imgPic);
        btnPic = view.findViewById(R.id.btnPic);

        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureCamera();
            }
        });
        checkPermission();
        return view;
    }

    private void captureCamera() {
        String state = Environment.getExternalStorageState();

        if(!Environment.MEDIA_MOUNTED.equals(state)) {
            return;
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = createFileName();
            if(photoFile != null) {
                Uri providerUri = FileProvider.getUriForFile(getContext(), getActivity().getPackageName(), photoFile);
                mProviderUri = providerUri;
                intent.putExtra(MediaStore.EXTRA_OUTPUT, providerUri);

                startActivityForResult(intent, REQ_TAKE_PHOTO);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Database db = Database.getInstance(getActivity());

        switch(requestCode) {
            case REQ_TAKE_PHOTO:
                if(resultCode == Activity.RESULT_OK) {
                    gallaryAddPic();

                    imgPic.setImageURI(mProviderUri);
                } else {
                    Toast.makeText(getContext(), "사진촬영을 취소하였습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        } // End Switch
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void gallaryAddPic() {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

        File file = new File(mCurrentImageFilePath);
        Uri contentUri = Uri.fromFile(file);
        intent.setData(contentUri);
        getActivity().sendBroadcast(intent);

        Toast.makeText(getContext(), "앨범에 사진이 추가되었습니다.", Toast.LENGTH_SHORT).show();
    }

    private File createFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = timeStamp + ".jpg";

        File myDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "item");
        if (!myDir.exists()) {
            myDir.mkdir();
        }

        File imageFile = new File(myDir, fileName);
        mCurrentImageFilePath = imageFile.getAbsolutePath();

        return imageFile;
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            // 권한동의 체크
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)
            ) {
                DialogUtil.showDialog(getContext(),
                        "알림",
                        "권한이 거부되었습니다. 직접 권한을 허용하세요.",
                        "설정",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 어플 설정으로 이동
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
                                startActivity(intent);
                            }
                        },
                        "취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 닫기
                                getActivity().finish();
                            }
                        });
            } else {
                // 권한동의 팝업 표시 요청
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, 1111);
            }
        }
    } // End checkPermission

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 1111:
                for (int i = 0; i < grantResults.length; i++) {
                    if(grantResults[i] < 0) {
                        Toast.makeText(getContext(), "해당 권한을 활성화 하셔야 합니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

    }
}
