package kr.ac.hansung.criminallntent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Owner on 2016-03-26.
 */
public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId(){ return mId; }
    public String getTitle(){ return mTitle; }
    public void setTitle(String title){ mTitle = title; }

    public Date getDate(){
        return mDate;
    }
    public void setDate(Date date){
        mDate = date;
    }
    public boolean ismSolved(){
        return mSolved;
    }
    public void setSolved(boolean solved){
        mSolved = solved;
    }

}
