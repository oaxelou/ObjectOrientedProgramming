/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*   UnsupportedFileFormatException class:
*   Objects occured are Exceptions used in case format of reading file is incorrect.
* 
*/

package ce325.hw2;

public class UnsupportedFileFormatException extends java.lang.Exception{
  public UnsupportedFileFormatException(){
    super();
  }

  public UnsupportedFileFormatException(String msg){
    super(msg);
  }
}
