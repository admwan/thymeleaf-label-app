package net.spikesync.lga.model;

public enum Country {
    FRANCE("France", "fr"),
    GERMANY("Germany", "de"),
    SPAIN("Spain", "es"),
    ITALY("Italy", "it"),
    BELGIUM("Belgium", "be"),
    NETHERLANDS("Netherlands", "nl"),
    PORTUGAL("Portugal", "pt"),
    POLAND("Poland", "pl"),
    SWEDEN("Sweden", "se"),
    FINLAND("Finland", "fi"),
    DENMARK("Denmark", "dk"),
    AUSTRIA("Austria", "at"),
    IRELAND("Ireland", "ie"),
    GREECE("Greece", "gr"),
    CZECH_REPUBLIC("Czech Republic", "cz"),
    HUNGARY("Hungary", "hu"),
    SLOVAKIA("Slovakia", "sk"),
    CROATIA("Croatia", "hr"),
    BULGARIA("Bulgaria", "bg"),
    ROMANIA("Romania", "ro"),
    LUXEMBOURG("Luxembourg", "lu"),
    SLOVENIA("Slovenia", "si"),
    LITHUANIA("Lithuania", "lt"),
    LATVIA("Latvia", "lv"),
    ESTONIA("Estonia", "ee"),
    CYPRUS("Cyprus", "cy"),
    MALTA("Malta", "mt");

    private final String displayName;
    private final String code;

    Country(String displayName, String code) {
        this.displayName = displayName;
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCode() {
        return code;
    }
}
