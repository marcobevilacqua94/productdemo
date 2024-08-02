package org.ingestor;

import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.json.JsonObject;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.TimeZone;

public class DLDocGenerator2 implements DocGenerator {


//     "ContextArea": "Rollers",
//             "WorkingArea": "FL",
//             "RollerLabel": "R04",
//             "Force": 8.5,
//             "ForceDirection": "ABSOLUTE_FORCE",
//             "BaseTopic": "C1/Rollers/FL/R04/Roller_Sample_v1",
//             "EventType": "Roller_Sample_v1",
//             "UID": "2933cdda-76cb-46b6-8f07-cdf4e119ede5",
//             "AcquisitionTimestamp": "2023-06-17T14:27:58.132Z",
//             "SiteCode": "C1",
//             "Quality": {
//        "Status": "OK",
//                "Details": []
//    }
    public JsonObject generateFrame(){

        return JsonObject.create()
                .put("frame_frame_encap_type", RandomStringUtils.randomNumeric(2))
                .put("frame_frame_time", generateAcquisitionTimestamp())
                .put("frame_frame_offset_shift", RandomStringUtils.randomNumeric(10))
                .put("frame_frame_time_epoch", RandomStringUtils.randomNumeric(10))
                .put("frame_frame_time_delta", RandomStringUtils.randomNumeric(10))
                .put("frame_frame_time_delta_displayed", RandomStringUtils.randomNumeric(10))
                .put("frame_frame_time_relative", RandomStringUtils.randomNumeric(10))
                .put("frame_frame_number", RandomStringUtils.randomNumeric(1))
                .put("frame_frame_len", RandomStringUtils.randomNumeric(2))
                .put("frame_frame_cap_len", RandomStringUtils.randomNumeric(2))
                .put("frame_frame_marked", returnRandomBool())
                .put("frame_frame_ignored", returnRandomBool())
                .put("frame_frame_protocols", "mtp3:sccp:tcap:gsm_map");
    }

    public JsonObject generateMtp3(){

        return JsonObject.create()
                .put("text", JsonArray.from(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)))
                .put("mtp3_mtp3_network_indicator", "0x" + RandomStringUtils.randomNumeric(8))
                .put("mtp3_mtp3_spare","0x" + RandomStringUtils.randomNumeric(8))
                .put("mtp3_mtp3_service_indicator","0x" + RandomStringUtils.randomNumeric(8))
                .put("mtp3_mtp3_pc",JsonArray.from(RandomStringUtils.randomNumeric(4), RandomStringUtils.randomNumeric(4)))
                .put("mtp3_mtp3_dpc",RandomStringUtils.randomNumeric(4))
                .put("q708_q708_sanc",RandomStringUtils.randomNumeric(4))
                .put("(q708_q708_ispc_name",RandomStringUtils.randomAlphabetic(10))
                .put("q708_q708_ispc_operator_name", RandomStringUtils.randomAlphabetic(10))
                .put("mtp3_mtp3_opc",RandomStringUtils.randomNumeric(4))
                .put("q708_q708_sanc",RandomStringUtils.randomNumeric(4))
                .put("q708_q708_ispc_name",RandomStringUtils.randomAlphabetic(10))
                .put("q708_q708_ispc_operator_name",RandomStringUtils.randomAlphabetic(20))
                .put("mtp3_mtp3_sls",RandomStringUtils.randomNumeric(1));
    }

    public JsonObject generateGsmMap(){
        return JsonObject.create()
                .put("gsm_map_gsm_map_old_Component",RandomStringUtils.randomNumeric(1))
                .put("gsm_map_gsm_old_invoke_element", RandomStringUtils.randomAlphabetic(4))
                .put("gsm_map_gsm_old_invokeID",RandomStringUtils.randomNumeric(1))
                .put("gsm_map_gsm_old_opCode",RandomStringUtils.randomNumeric(1))
                .put("gsm_map_gsm_old_localValue",RandomStringUtils.randomNumeric(2))
                .put("gsm_map_gsm_map_msisdn", RandomStringUtils.randomNumeric(2) + ":" + RandomStringUtils.randomNumeric(2) + ":" + RandomStringUtils.randomNumeric(2) + ":" + RandomStringUtils.randomNumeric(2) + ":" + RandomStringUtils.randomNumeric(2) + ":" + RandomStringUtils.randomNumeric(2) + ":" + RandomStringUtils.randomNumeric(2))
                .put("gsm_map_gsm_map_extension", returnRandomBool())
                .put("gsm_map_gsm_map_nature_of_number",RandomStringUtils.randomNumeric(1))
                .put("gsm_map_gsm_map_number_plan",RandomStringUtils.randomNumeric(1))
                .put("e164_e164_msisdn",RandomStringUtils.randomNumeric(12))
                .put("e164_e164_country_code",RandomStringUtils.randomNumeric(2));

    }

    public JsonObject generateTcap(){
        return JsonObject.create()
                .put("tcap_tcap_begin_element", RandomStringUtils.randomAlphabetic(4))
                .put("tcap_tcap_tid", RandomStringUtils.randomAlphabetic(16))
                .put("text", RandomStringUtils.randomAlphabetic(12))
                .put("tcap_tcap_otid", RandomStringUtils.randomAlphabetic(16))
                .put("tcap_tcap_oid", RandomStringUtils.randomAlphabetic(16))
                .put("tcap_tcap_dialogueRequest_element", RandomStringUtils.randomAlphabetic(4))
                .put("tcap_tcap_application_context_name", RandomStringUtils.randomAlphabetic(16))
                .put("tcap_tcap_components",RandomStringUtils.randomNumeric(1))
                .put("tcap_tcap_Component",RandomStringUtils.randomNumeric(1))
                .put("tcap_tcap_invoke_element", RandomStringUtils.randomAlphabetic(4))
                .put("tcap_tcap_invokeID",RandomStringUtils.randomNumeric(1))
                .put("tcap_tcap_opCode",RandomStringUtils.randomNumeric(1))
                .put("tcap_tcap_localValue",RandomStringUtils.randomNumeric(2))
                .put("text", "Parameter- (0x" + RandomStringUtils.randomNumeric(2) + ")")
                .put("tcap_tcap_msgtype", "ex" + RandomStringUtils.randomNumeric(8))
                .put("tcap_tcap_len",RandomStringUtils.randomNumeric(1))
                .put("tcap_tcap_data",RandomStringUtils.randomNumeric(2));
    }

    public JsonObject generateSccp(){
        return JsonObject.create()
                .put("sccp_sccp_message_type","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_class","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_handling","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_variable_pointer1",RandomStringUtils.randomNumeric(1))
                .put("sccp_sccp_variable_pointer2",RandomStringUtils.randomNumeric(2))
                .put("sccp_sccp_variable_pointer3",RandomStringUtils.randomNumeric(2))
                .put("sccp_sccp_parameter_length", JsonArray.from(RandomStringUtils.randomNumeric(2), RandomStringUtils.randomNumeric(2), RandomStringUtils.randomNumeric(2)))
                .put("text", JsonArray.from(RandomStringUtils.randomAlphabetic(16), RandomStringUtils.randomAlphabetic(16)))
                .put("text1", JsonArray.from(RandomStringUtils.randomAlphabetic(16), RandomStringUtils.randomAlphabetic(16), RandomStringUtils.randomAlphabetic(16), RandomStringUtils.randomAlphabetic(16)))
                .put("sccp_sccp_called_reserved","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_called_ri","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_called_gti","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_called_ssni","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_called_pci","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_called_ssn",RandomStringUtils.randomNumeric(1))
                .put("sccp_sccp_ssn", JsonArray.from(RandomStringUtils.randomNumeric(1), RandomStringUtils.randomNumeric(1)))
                .put("sccp_sccp_linked_dissector", JsonArray.from(RandomStringUtils.randomAlphabetic(4), RandomStringUtils.randomAlphabetic(4)))
                .put("sccp_sccp_called_tt","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_called_np","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_called_es","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_called_nai","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_called_digits",RandomStringUtils.randomNumeric(12))
                .put("sccp_sccp_digits",RandomStringUtils.randomNumeric(12))
                .put("sccp_sccp_called_digits_length",RandomStringUtils.randomNumeric(2))
                .put("e164_e164_country_cole",RandomStringUtils.randomNumeric(2))
                .put("sccp_sccp_calling_reserved","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_calling_ri", RandomStringUtils.randomAlphabetic(16))
                .put("sccp_sccp_calling_gti","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_calling_ssni","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_calling_pci","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_calling_ssn",RandomStringUtils.randomNumeric(1))
                .put("sccp_sccp_calling_tt","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_calling_np","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_calling_es","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_calling_nai","0x" + RandomStringUtils.randomNumeric(8))
                .put("sccp_sccp_calling_digits",RandomStringUtils.randomNumeric(12))
                .put("sccp_sccp_digits",RandomStringUtils.randomNumeric(12))
                .put("sccp_sccp_calling_digits_length",RandomStringUtils.randomNumeric(2))
                .put("e164_e164_country_code",RandomStringUtils.randomNumeric(2));

    }

    public JsonObject generateDoc(){
        return JsonObject.create()
                .put("timestamp", RandomStringUtils.randomNumeric(8))
                .put("layers", JsonObject.create()
                        .put("frame", generateFrame())
                        .put("mtp3", generateMtp3())
                        .put("sccp", generateSccp())
                        .put("tcap", generateTcap())
                        .put("gsm_map", generateGsmMap())
                );
    }


    private final Random rand = new Random();
    private final String[] qualities = {
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "OK",
            "WARNING",
            "ERROR",
            "CRITICAL",
            "FATAL"
    };

    private final String[] forceDirections = {
            "ABSOLUTE_FORCE",
            "STRONG_FORCE",
            "MEDIUM_FORCE",
            "WEAK_FORCE",
            "NO_FORCE"
    };

    private final String loremSentence = "Lorem ipsum repellendus necessitatibus culpa blanditiis consequuntur dolor.";

    private String returnRandom(String[] list) {
        return list[rand.nextInt(list.length)];
    }

    private boolean returnRandomBool(){
        return rand.nextBoolean();
    }

    private String generateIPAddress() {
        return rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256);
    }

    private String generateContextArea(){
        return RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(4 + rand.nextInt(10));
    }

    private String generateWorkingArea(){
        return RandomStringUtils.randomAlphabetic(2).toUpperCase();
    }

    private String generateRollerLabel(){
        return RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomNumeric(2);
    }

    private String generateSiteCode(){
        return RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomNumeric(1);
    }

    private String generateCategory(){
        return RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(4 + rand.nextInt(12));
    }

    private String generateEventType(){
        return RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(4 + rand.nextInt(24));
    }

    private String generateUserAgent() {
        return RandomStringUtils.randomAlphabetic(6).toUpperCase() + "." +
                RandomStringUtils.randomNumeric(3) + RandomStringUtils.randomAlphabetic(3).toUpperCase() + "." +
                RandomStringUtils.randomNumeric(3) + RandomStringUtils.randomAlphabetic(3).toUpperCase() + "." +
                RandomStringUtils.randomNumeric(3) + RandomStringUtils.randomAlphabetic(4).toUpperCase();

    }

    private String generateOid(){
        return RandomStringUtils.randomAlphanumeric(24);
    }

    private String generateNatId(){
        return RandomStringUtils.randomNumeric(10);
    }

    private Double generateForce(){ return (double) rand.nextInt(100) / 10;}
    private String generateDate(){
        int year = 2013 + rand.nextInt(10);
        int month = rand.nextInt(12) + 1;
        int day;
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = rand.nextInt(31) + 1;
                break;
            case 2:
                switch(year){
                    case 2016:
                    case 2020:
                        day = rand.nextInt(29) + 1;
                        break;
                    default:
                        day = rand.nextInt(28) + 1;
                }
                break;
            default:
                day = rand.nextInt(30) + 1;
        }
        return year + "-" + (month < 10 ? ("0" + month) : month) + "-" + (day < 10 ? ("0" + day) : day);
    }

    private Long generateApiId(){
        return rand.nextInt(100) == 99 ? null : Long.parseLong(generateNatId());
    }

    private String generateObjectChanges(){
        return loremSentence.repeat(1 + rand.nextInt(6));
    }

    private JsonArray generateDetails(String status) {
        JsonArray result = JsonArray.create();
        if(Objects.equals(status, "OK")){
            return result;
        }
        for (int i = 0; i < rand.nextInt(5); i++){
            result.add(RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphabetic(4 + rand.nextInt(24)));
        }
        return result;
    }

    private String generateAcquisitionTimestamp(){
        long millis = System.currentTimeMillis() + rand.nextInt(1000000000);
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

}
