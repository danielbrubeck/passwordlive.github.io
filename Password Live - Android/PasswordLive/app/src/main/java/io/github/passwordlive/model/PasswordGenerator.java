package io.github.passwordlive.model;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by brubecd on 8/12/2015.
 */
public class PasswordGenerator {
    public String compute(String secretKey, String whatFor, int passwordLength, boolean useUpperCase, boolean useLowerCase, boolean useNumbers, boolean useSpecialCharacters) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        int modifier = ( useUpperCase ? 3 : 0 ) + ( useLowerCase ? 5 : 0 ) + ( useNumbers ? 7 : 0 ) + ( useSpecialCharacters ? 11 : 0 );

        byte[] inputData = ( whatFor + String.valueOf(modifier) + secretKey ).getBytes(Constants.ENCODE_VALUE);
        md.update(inputData);
        String baseResult = Base64.encodeToString(md.digest(), Base64.DEFAULT);

        byte[] inputData2 = ( secretKey + String.valueOf(modifier) + whatFor ).getBytes(Constants.ENCODE_VALUE);
        md.update(inputData2);
        String baseResult2 = Base64.encodeToString(md.digest(), Base64.DEFAULT);

        String p = (baseResult.substring(0, baseResult.length() - 1)) + (baseResult2.substring(0, baseResult2.length() - 1));
        
        String[][] charMap = {
                                { "A", "U", "o", "5", "!" },  
                                { "B", "Z", "c", "6", "_" },  
                                { "C", "L", "n", "5", "^" },  
                                { "D", "K", "l", "9", ";" },  
                                { "E", "R", "h", "3", "?" },  
                                { "F", "T", "w", "1", "}" },  
                                { "G", "W", "t", "1", "-" },                   
                                { "H", "M", "b", "7", "^" },  
                                { "I", "E", "" , "5", ":" },  
                                { "J", "" , "h", "8", "%" },  
                                { "K", "Z", "" , "6", "," },  
                                { "L", "B", "g", "" , "#" },   
                                { "M", "Q", "a", "0", "@" },  
                                { "N", "" , "z", "" , ""  },  
                                { "O", "J", "o", "4", "%" },  
                                { "P", "K", "m", "0", ""  },  
                                { "Q", "" , "k", "8", "{" },  
                                { "R", "A", "t", "4", "-" },  
                                { "S", "" , "s", "6", ""  },  
                                { "T", "S", "v", "0", "|" },  
                                { "U", "V", "g", "9", ""  },  
                                { "V", "D", "" , "3", "=" },  
                                { "W", "" , "p", "0", "@" },  
                                { "X", "D", "z", "1", ")" },  
                                { "Y", "" , "u", "6", ""  },  
                                { "Z", "V", "" , "9", "(" },  
                                { "a", "I", "i", "2", ""  },  
                                { "b", "" , "c", "5", "." },  
                                { "c", "J", "r", "3", ""  },  
                                { "d", "P", "d", "6", "!" },  
                                { "c", "J", "r", "3", ""  },  
                                { "d", "P", "d", "6", "!" },  
                                { "e", "S", "l", "4", ""  },  
                                { "f", "N", "j", "2", "*" },  
                                { "g", "X", "" , "4", "'" },  
                                { "h", "F", "e", "7", "+" },  
                                { "i", "A", "y", "8", "/" },  
                                { "j", "B", "e", "" , ""  },  
                                { "k", "" , "" , "7", "\\" },   
                                { "l", "O", "b", "3", "?" },  
                                { "m", "R", "q", "" , "#" },  
                                { "n", "H", "" , "0", "}" },  
                                { "o", "L", "v", "7", "." },  
                                { "p", "F", "" , "4", ")" },  
                                { "q", "" , "i", "8", ""  },  
                                { "r", "W", "" , "5", "$" },  
                                { "s", "" , "w", "9", ""  },  
                                { "t", "C", "u", "2", "@" },  
                                { "u", "H", "f", "6", ""  },  
                                { "v", "" , "q", "9", "'" },  
                                { "w", "O", "x", "2", "/" },  
                                { "x", "T", "j", "2", "," },  
                                { "y", "X", "m", "8", ";" },  
                                { "z", "Y", "u", "1", ""  },  
                                { "0", "C", "" , "4", "|" },  
                                { "1", "" , "d", "5", "=" },  
                                { "2", "Q", "" , "8", "_" },  
                                { "3", "G", "x", "7", "*" },  
                                { "4", "M", "s", "3", "{" },  
                                { "5", "E", "n", "1", ""  },  
                                { "6", "I", "a", "7", "@" },  
                                { "7", "N", "k", "2", ":" },  
                                { "8", "G", "r", "3", "\\" },  
                                { "9", "U", "p", "1", "(" },  
                                { "+", "Y", "f", "9", "+" },  
                                { "/", "P", "y", "0", "$" }   
                            };

            int[] charMapLocations = new int[p.length()];

            for (int i = 0; i < p.length(); i++) {
                for (int c = 0; c < charMap.length; c++) {
                    if (p.substring(i, i+1).equals(charMap[c][0]) ) {
                        charMapLocations[i] = c;
                        break;
                    }
                }
            }

            int x = 0;
            int y = 1;
            int N;
            
            StringBuilder passwordBuilder = new StringBuilder();
            int charMapLocationsIndex = -1;
            int currentPasswordLength = 0;

            // upper, lower, numbers, special chars
            boolean[] containsElement = { !useUpperCase, !useLowerCase, !useNumbers, !useSpecialCharacters };

            if ( useLowerCase || useUpperCase || useNumbers || useSpecialCharacters ) {
                while ( currentPasswordLength < passwordLength ) {
                    if ( charMapLocationsIndex == (charMapLocations.length - 1) ) {
                        charMapLocationsIndex = 0;
                    } else {
                        charMapLocationsIndex++;
                    }

                    N = charMapLocations[charMapLocationsIndex];
                    x = (x * 4 + y + N) / 4;
                    y = ((x * 4 + y + N) % 4) + 1;

                    while (x > (charMap.length - 1)) {
                        x = x - (charMap.length );
                    }

                    if ( !useUpperCase && ( y == 1 ) ) 
                        continue;
                    if ( !useLowerCase && ( y == 2 ) )
                        continue;
                    if ( !useNumbers && ( y == 3 ) )
                        continue;
                    if ( !useSpecialCharacters && ( y == 4 ) )
                        continue;

                    if (charMap[x][y] != "") {
                        if ((currentPasswordLength > (Constants.MIN_LENGTH - 5)) && (currentPasswordLength < Constants.MIN_LENGTH )) {
                            if ((containsElement[0] == false) || (containsElement[1] == false) || (containsElement[2] == false) || (containsElement[3] == false) ) {
                                if ( containsElement[y-1] ) {
                                    continue;
                                }
                            }
                        }

                        passwordBuilder.append(charMap[x][y]);
                        currentPasswordLength++;

                        containsElement[y-1] = true;
                    }
                }
            }
        return passwordBuilder.toString();
    }
}
