package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
import net.sf.saxon.functions.IndexOf;

public class GuitarHero {

    public static GuitarString[] string37 = new GuitarString[37];
//    string37[1] = new GuitarString(440.0);
    public static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static double[] Concert = new double[37];

    // set concert frequency of 37 notes;
    public static void setConcert() {
        for (int i=0; i<37; i++){
            Concert[i] = 440.0 * Math.pow(2, (i -24) / 12.0);
        }
    }

    // set guitar string of 37 notes
    public static void setString() {
        for (int i=0; i<37; i++) {
            string37[i] = new GuitarString(Concert[i]);
        }
    }


    public static void main(String[] args) {
        setConcert();
        setString();
        while (true) {


            GuitarString THEstring;
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) == -1) {
                   continue;
                }
                THEstring = string37[keyboard.indexOf(key)];
                THEstring.pluck();
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (GuitarString x : string37) {
                sample = sample + x.sample();;
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString x : string37) {
                x.tic();;
            }
        }
    }
}