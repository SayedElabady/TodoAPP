package listener;

import java.util.ArrayList;

import model.ToDo;

/**
 * Created by Sayed on 5/19/2017.
 */

public interface TodoLoadListener {


    void onDataLoaded(ArrayList<ToDo> dataList);


}
