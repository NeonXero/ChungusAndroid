package net.neonlotus.chungusadmin.roompersistence.Models.data_diagram;

import net.neonlotus.chungusadmin.tools.Constants;
import net.neonlotus.chungusadmin.tools.Utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events_table"/*, foreignKeys = {
        @ForeignKey(entity = DBTenant.class,
                parentColumns = "tenantId", childColumns = "tenant_id",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = DBAddress.class,
                parentColumns = "addressId", childColumns = "address_id",
                onDelete = ForeignKey.CASCADE)}*/
)
public class DBEvent {

    @NonNull
    @PrimaryKey
    private String eventId;

    private String eventName;

    private String eventDescription;

    @ColumnInfo(name = "tenant_id")
    private String tenantId; //FK

    private String startDate;

    private String endDate;

    @ColumnInfo(name = "address_id")
    private String addressId; //FK

    private Boolean isStaff;

    private Boolean isRegistered;

    //https://developer.android.com/reference/android/arch/persistence/room/Relation

    public DBEvent() {
    }


    @NonNull
    public String getEventId() {
        return eventId;
    }

    public void setEventId(@NonNull String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName != null ? eventName : "";
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }


    public Boolean getStaff() {
        return isStaff;
    }

    public void setStaff(Boolean staff) {
        isStaff = staff;
    }

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    //==== HELPER METHODS ====
    public String getShortMonthName() {
        Date local = Utils.convertUTCToLocal(startDate);
        String localStartDate = Constants.df.format(local);

        if (localStartDate != null && !localStartDate.isEmpty()) {
            Calendar cal = Calendar.getInstance();

            try {
                cal.setTime(Constants.df.parse(localStartDate));
                return cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        }

        return "";
    }

    public String getNumericDay() {
        Date local = Utils.convertUTCToLocal(startDate);
        String localStartDate = Constants.df.format(local);

        if (localStartDate != null && !localStartDate.isEmpty()) {
            Calendar cal = Calendar.getInstance();

            try {
                cal.setTime(Constants.df.parse(localStartDate));
                int daynumber = cal.get(Calendar.DAY_OF_MONTH);
                return "" + daynumber;
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        }

        return "";
    }

    public String getYear() {
        Date local = Utils.convertUTCToLocal(startDate);
        String localStartDate = Constants.df.format(local);

        if (localStartDate != null && !localStartDate.isEmpty()) {
            Calendar cal = Calendar.getInstance();

            try {
                cal.setTime(Constants.df.parse(localStartDate));
                int year = cal.get(Calendar.YEAR);
                return "" + year;
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        }

        return "";
    }
}