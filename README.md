 -----------------------
| Qr-Generator-Scanner  |                                                                        
 -----------------------               
 
 
# Qr Code Generator(HTML & JS) --------- Available in Qr Code Generator directory
 
# Qr Code Scanner (Android Application)
 
 
 # Add QRCodeReaderView dependency to your build.gradle:
 
    compile 'com.dlazaro66.qrcodereaderview:qrcodereaderview:2.0.3'
 
 # Add to your layout file:
 
    <com.dlazaro66.qrcodereaderview.QRCodeReaderView
         android:id="@+id/qrdecoderview"
         android:layout_width="match_parent"
         android:layout_height="match_parent" />
               
# Implement OnQRCodeReadListener in your Activity       

   Output will be available in overriden method onQRCodeRead()

----   This code is available in ScanActivity.java
   Scanner library reference:  https://android-arsenal.com/details/1/1891
 

# Firebase Database 
    To update the scanned Qr code data
# Firebase ui  
    To display the qr code details  ( one Qr code for each participant or user)
# Without Qr Scan
    can update the Qr code data by using id of Qr code (without qr scan)
