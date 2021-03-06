// A client that uses the synthesizer package to replicate a plucked guitar string sound
import java.util.LongSummaryStatistics;
import java.util.Map;
import synthesizer.GuitarString;
public class GuitarHeroLite {
      public static void main(String[] args) {

          // create two guitar strings, for concert A and C
          String keyboard ="q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
          GuitarString[] guitar = new GuitarString[keyboard.length()];
          for(int i=0;i<keyboard.length();i++)
          {
              char ch = keyboard.charAt(i);
              double freq = 440*Math.pow(2, (i-24)/12);
              guitar[i] = new GuitarString(freq);
          }
          //double CONCERT_A = 440.0;
         // double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0); 
        //  synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
        //  synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);

          while (true) {

              // check if the user has typed a key; if so, process it   
              if (StdDraw.hasNextKeyTyped()) {
                  char key = StdDraw.nextKeyTyped();
                  int i = keyboard.indexOf(key);
                  if(i>0)
                  {
                      guitar[i].pluck();
                  }
              }

              // compute the superposition of samples
             // double sample = stringA.sample() + stringC.sample();
              double sample =0.0;
              for(GuitarString string : guitar)
              {
                  sample += string.sample();
              }
              // play the sample on standard audio
              // note: this is just playing the double value YOUR GuitarString
              //       class is generating. 
              StdAudio.play(sample);
  
              // advance the simulation of each guitar string by one step   
              for(GuitarString string : guitar)
              {
                  string.tic();
              }
          }
       }
  }

