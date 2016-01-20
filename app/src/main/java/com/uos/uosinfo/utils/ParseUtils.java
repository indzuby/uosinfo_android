package com.uos.uosinfo.utils;

import com.parse.ParseObject;
import com.uos.uosinfo.domain.Library;
import com.uos.uosinfo.domain.PathFinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016-01-12.
 */
public class ParseUtils {

    public static List<PathFinder> parsePathFinders(List<ParseObject> pass) {
        List<PathFinder> pathFinders = new ArrayList<>();
        for(ParseObject object : pass) {
            PathFinder pathFinder = new PathFinder();
            pathFinder.setStartDatetime(object.getDate("startDatetime"));
            pathFinder.setEndDatetime(object.getDate("endDatetime"));
            pathFinder.setObjectId(object.getObjectId());
            pathFinder.setDisplayOrder(object.getInt("displayOrder"));
            pathFinders.add(pathFinder);
        }
        return pathFinders;
    }
    public static List<Library> parseLibrary(List<ParseObject> libraries) {
        List<Library> lib = new ArrayList<>();
        for(ParseObject object : libraries) {
            Library library = new Library();
            library.setTitle(object.getString("title"));
            library.setCreateDatetime(object.getCreatedAt());
            library.setUrl(object.getString("url"));
            library.setContent(object.getString("content"));
            library.setObjectId(object.getObjectId());
            library.setIsNew(object.getBoolean("new"));
            lib.add(library);
        }
        return lib;
    }
}
