/**
 * 2011 Foxykeep (http://datadroid.foxykeep.com)
 * <p>
 * Licensed under the Beerware License : <br />
 * As long as you retain this notice you can do whatever you want with this stuff. If we meet some
 * day, and you think this stuff is worth it, you can buy me a beer in return
 */

package com.foxykeep.datadroidpoc.data.factory;

import com.foxykeep.datadroid.exception.DataException;
import com.foxykeep.datadroidpoc.config.JSONTag;
import com.foxykeep.datadroidpoc.data.model.Phone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class PhoneListFactory {

    private PhoneListFactory() {
        // No public constructor
    }

    public static ArrayList<Phone> parseResult(final String wsResponse) throws DataException {
        final ArrayList<Phone> phoneList = new ArrayList<Phone>();

        try {
            final JSONObject parser = new JSONObject(wsResponse);
            final JSONObject jsonRoot = parser.getJSONObject(JSONTag.CRUD_PHONE_LIST_ELEM_PHONES);
            final JSONArray jsonPhoneArray = jsonRoot
                    .getJSONArray(JSONTag.CRUD_PHONE_LIST_ELEM_PHONE);
            final int size = jsonPhoneArray.length();
            for (int i = 0; i < size; i++) {
                final JSONObject jsonPhone = jsonPhoneArray.getJSONObject(i);
                final Phone phone = new Phone();

                phone.serverId = jsonPhone.getLong(JSONTag.CRUD_PHONE_LIST_ELEM_ID);
                phone.name = jsonPhone.getString(JSONTag.CRUD_PHONE_LIST_ELEM_NAME);
                phone.manufacturer = jsonPhone.getString(JSONTag.CRUD_PHONE_LIST_ELEM_MANUFACTURER);
                phone.androidVersion = jsonPhone
                        .getString(JSONTag.CRUD_PHONE_LIST_ELEM_ANDROID_VERSION);
                phone.screenSize = jsonPhone.getDouble(JSONTag.CRUD_PHONE_LIST_ELEM_SCREEN_SIZE);
                phone.price = jsonPhone.getInt(JSONTag.CRUD_PHONE_LIST_ELEM_PRICE);

                phoneList.add(phone);
            }
        } catch (JSONException e) {
            throw new DataException(e);
        }

        return phoneList;
    }

}
