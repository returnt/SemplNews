package returnt.libraryjava;

/**
 * Created by returnt5 on 28.08.2015.
 */
public class SpliceString {

    private String spliceString = null;
    private String cutString = null;

    private void Cut(String str, String cut){

        cutString = str.replaceAll(cut, "");
    }

    private void Splice(String str, String cut, String paste){

        spliceString = str.replaceAll(cut, paste);
    }

    public String getSpliceString(String str, String cut, String paste){

        Splice(str, cut, paste);
        return spliceString;
    }

    public String getCutString(String str, String cut){

        Cut(str, cut);
        return cutString;
    }
}
