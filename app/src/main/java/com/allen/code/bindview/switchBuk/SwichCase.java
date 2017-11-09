package com.allen.code.bindview.switchBuk;

import android.util.SparseArray;

/**
 * Created by husongzhen on 17/11/9.
 */

public class SwichCase {

    private static final SparseArray<ICaseAction> caseArray = new SparseArray<>();

    public void action(int type) {
        ICaseAction action = caseArray.get(type);
        if (action != null) {
            action.onAction();
        }

    }

    private static final class Clazz {
        private static final SwichCase clazz = new SwichCase();
    }

    public static final SwichCase create() {
        return Clazz.clazz;
    }

    private SwichCase() {
    }

    public SwichCase scase(int type, ICaseAction action) {
        caseArray.append(type, action);
        return this;
    }


}
