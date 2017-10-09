package com.allen.code.bindview;

import com.allen.code.bindview_api.FHttp.RequestType;
import com.allen.code.bindview_api.FHttp.annotation.FHttp;
import com.allen.code.bindview_api.FHttp.annotation.UrlParams;

/**
 * 作者：husongzhen on 17/9/30 09:41
 * 邮箱：husongzhen@musikid.com
 */

@FHttp
public class ConstansUrl {




    @UrlParams(mName = "getHome", requestType = RequestType.GET)
    public String url1 = "https://mi.oneapm.com/mobile/app/555023/crashlog#/crashtrace/1248379";




}
