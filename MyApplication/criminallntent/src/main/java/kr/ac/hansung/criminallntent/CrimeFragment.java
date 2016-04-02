package kr.ac.hansung.criminallntent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by Owner on 2016-03-26.
 */
public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    //날짜 형식 DateFormat 설정 ex) Thursday, stp 12, 2013
    String time = android.text.format.DateFormat.format("EEEE, MMM dd, yyyy", System.currentTimeMillis()).toString();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_crime, parent,false);

        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(
                    CharSequence c ,int start, int defore, int count){
                mCrime.setTitle(c.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
            public void beforeTextChanged(
                    CharSequence c, int start, int before, int after){
                //이 메서드의 실행 코드는 여기서는 필요 없음
            }
            public void afterTextchanged(Editable c){
                //이 메서드의 실행 코드는 여기서는 필요 없음
            }
        });

        mDateButton = (Button)v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }
}
