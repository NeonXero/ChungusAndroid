package net.neonlotus.chungusadmin.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {
    // ========================
    // PERMISSION RELATED ITEMS
    // ========================
    public final static int ActiveTrackingLocationRequest = 1;
    public final static int MainActivityLocationRequest = 2;
    public final static int EmergencyContactRequest = 3;
    public final static int AddEmergencyContactRequest = 4;
    public final static int MainMapLocationRequest = 5;
    public final static int AddFriendContactRequest = 6;
    public final static int IssueMapLocationRequest = 7;


    // ========================
    // GEOFENCE RELATED ITEMS
    // ========================
    public final static int GEOFENCE_RADIUS_IN_METERS = 100;
    public final static int GEOFENCE_EXPIRATION_IN_MILLISECONDS = 1000 * 60 * 60;
    public final static int GEOFENCE_COUNT = 20;


    // ========================
    // FRAGMENT SETUP RELATED ITEMS
    // ========================
    public final static int NO_BUTTONS = 0;
    public final static int BACK_ARROW = 1;
    public final static int HAMBURGER_MENU = 2;
    public final static int NO_BAR = 3;


    // ========================
    // LOGIN RELATED ITEMS
    // ========================
    public final static String FACEBOOK_SOURCE = "facebook";
    public final static String SNAPCHAT_SOURCE = "snapchat";
    public final static String INSTAGRAM_SOURCE = "instagram";
    public final static String SIGNIN_SOURCE = "signin";


    // ========================
    // DATABASE RELATED ITEMS
    // ========================
    public final static String EVENT_DATABASE_NAME = "event_db";
    //bridgefy
    public final static String RESPONSE_DB_NAME = "response_db";
    //new consolidated db
    public final static String BUDDY_DB_NAME = "buddy_db";


    // ========================
    // BRIDGEFY RELATED ITEMS
    // New slim versions
    // ========================
    //message params
    public final static String TIME_TO_KILL = "timeToKill";
    public final static String TIME_TO_KILL_SLIM = "ttk";
    public final static String MESSAGE_ID = "msgID";
    public final static String MESSAGE_ID_SLIM = "id";
    public final static String MESSAGE = "msg";
    public final static String MESSAGE_SLIM = "m";
    public final static String CREATOR_ID = "creatorID";
    public final static String CREATOR_ID_SLIM = "cid";
    public final static String TYPE = "type";
    public final static String TYPE_SLIM = "t";
    public final static String BID = "bid";
    public final static String BID_SLIM = "bid";

    //incoming message types
    public final static String KILL = "kill";
    public final static String KILL_SLIM = "k";
    public final static String HELP = "help";
    public final static String HELP_SLIM = "h";
    public final static String RESPONSE = "response";
    public final static String RESPONSE_SLIM = "r";

    public final static String ERROR = "error";
    public final static String ERROR_SLIM = "e";

    public final static String CREATE_INCIDENT = "createIncident";
    public final static String CREATE_INCIDENT_SLIM = "I";
    public final static String CREATE_LOCATION = "createLocation";
    public final static String CREATE_LOCATION_SLIM = "L";
    public final static String CREATE_MESSAGE = "createMessage";
    public final static String CREATE_MESSAGE_SLIM = "M";
    public final static String CREATE_SOCIAL_MESSAGE = "createSocialMessage";
    public final static String CREATE_SOCIAL_MESSAGE_SLIM = "SM";


    // ========================
    // BRIDGEFY RELATED ITEMS (also issue status)
    // ========================
    public final static String ACTIVE_ISSUE = "ACTIVE";
    public final static String REQUEST_CANCEL = "REQUEST_CANCEL";
    public final static String CANCEL = "CANCEL";
    public final static String RESOLVED = "RESOLVED";


    // ========================
    // GOTENNA RELATED ITEMS
    // ========================
    public final static boolean useGotenna = false;


    // ========================
    // SEND HELP THINGS
    // ========================
    public final static boolean showHelp = false; // TODO: 2020-02-10 temp until we get flags from events
    public final static int HIDE_BUTTON = 1;
    public final static int SHOW_REGISTER = 2;
    //September '19 temporarily removing HELP features
    //Oct 31 '19 - bringing back
    public final static int SHOW_VIEW = 3;
    public final static int SHOW_HELP = 4;
    public final static int SHOW_UNREGISTER = 5;

    // ========================
    // MAP FLAGS
    // ========================
    public final static int MAP_SOCIAL = 1;
    public final static int MAP_EVENT = 2;
    public final static int FIT_NORMAL = 0;
    public final static int FIT_BEST = 1;
    public final static int FIT_ALL = 2;
    public final static int MRK_PROFILE = 1;
    public final static int MRK_NAME = 2;
    public final static int MRK_ICON = 3;

    // ========================
    // VERSION INFO
    // ========================
    public final static int VERSION_NAME = 1;
    public final static int VERSION_CODE = 2;
    public final static int VERSION_BOTH = 3;

    // ========================
    // DEBUG LOG RELATED ITEMS
    // ========================
    public final static boolean LOG_HTTP = false;
    public final static boolean LOG_REST_REQUEST = true;            //T
    public final static boolean LOG_GEOFENCE = false;
    public final static boolean LOG_UPSERT_FRIEND_MSG = false;
    public final static boolean LOG_UPSERT_FRIEND = false;
    public final static boolean LOG_UPSERT_EVENT = false;
    public final static boolean LOG_UPSERT_EVENT_CH_MSG = true;     //T
    public final static boolean LOG_DELETE_FRIEND_MSG = false;
    public final static boolean LOG_UPDATE_DEVICE_LOC = false;
    public final static boolean LOG_DELETE_EVENT = false;
    public final static boolean LOG_ADD_ISSUE = false;
    public final static boolean LOG_DELETE_FRNDANDMSG = false;
    public final static boolean LOG_REST_RESPONSE = true;


    // ========================
    // OTHER
    // ========================
    public final static int ForegroundId = 1337;
    public final static int NotifIDIssue = 1234;
    public final static int NotifIDEventMessage = 2345;
    public final static int NotifIDMessage = 3456;
    public final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public final static String DATE_FORMAT_OTHER = "yyyy-MM-dd'T'HH:mm:ss";
    public final static String DATE_FORMAT_SHARE = "EE  MMM d  HH:mm:ss z YYYY";

    public final static String SHORT_DATE_FORMAT_DASH = "MM-dd-yyyy";
    public final static String SHORT_DATE_FORMAT_SLASH = "MM/dd/yyyy";
    ////DD/MM/YYYY standard hour/minute time format + am/pm for under-chat message
    public final static String CHAT_MESSAGE_DATE = "M/d/yyyy";
    public final static String CHAT_MESSAGE_TIME = "h:mm a";
    public final static String CHAT_MESSAGE_DATE_NOYEAR = "M/d";
    public final static String CHAT_MESSAGE_DATE_SHORTYEAR = "M/d/yy";
    public final static int MARKER_ICON_SIZE = 100;
    public final static int MARKER_ICON_SIZE_LG = 150;
    public final static int MARKER_ICON_SIZE_XL = 200;
    public final static int BridgefyTimeToKillMinutes = 5;
    public final static int GeofencePollMinutes = 5;
    public final static int CONNECTION_DELAY = 750;

    public final static int ACTIVE_TRACKING_INTERVAL = 4 * 60 * 1000;
    public final static int ACTIVE_TRACKING_INTERVAL_FAST = 2 * 60 * 1000;
    public final static int GEOFENCING_TRACKING_INTERVAL = 20 * 60 * 1000;
    public final static int GEOFENCING_TRACKING_INTERVAL_FAST = 10 * 60 * 1000;
    public final static int LOITER_DELAY = 60 * 1000;

    public final static int INCIDENT_ERROR_CODE = 1;
    public final static int LOCATION_ERROR_CODE = 2;
    public final static int MESSAGE_ERROR_CODE = 3;
    public final static int SOCIAL_ERROR_CODE = 4;

    public static DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault());
    public static DateFormat df2 = new SimpleDateFormat(Constants.DATE_FORMAT_OTHER, Locale.getDefault());
    public static DateFormat df3 = new SimpleDateFormat(DATE_FORMAT_SHARE, Locale.getDefault());

    public final static int RECENT_FRAGMENT_SIZE = 15;

    public final static String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    public final static int VIEWTYPE_SOCIAL = 1;
    public final static int VIEWTYPE_BESTBUDDY = 2;

    public final static double USA_LAT = 39.5;
    public final static double USA_LNG = -98.35;
    public final static int USA_ZOOM = 5;

    // ========================
    // FCM messages
    // ========================
    public final static String LOCATION_UPDATE_FRIEND = "location-update-friend";
    public final static String LOCATION_UPDATE_ISSUE = "location-update-issue";
    public final static String NEW_ISSUE = "new-issue";
    public final static String NEW_MESSAGE_FRIEND = "new-message-friend";
    public final static String NEW_MESSAGE_EVENT = "new-message-event";
    public final static String NEW_MESSAGE_ALERT = "new-alert-event";
    public final static String BACKGROUND = "background";
}
