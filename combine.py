from threading import Thread



def connection():
    host=''
    port=8134
    def setupServer():
        s = socket(AF_INET,SOCK_STREAM)
        print("Socket Created")
        try:
            s.bind((host,port))
            s.listen(1)
        except socket.error as msg:
            print(msg)
        return s

    def dataTransfer():
      while True:
        try: 
         conn,address = s.accept()
        except:
         print('nothing')
        try :
            dataa = 'c'
            dataa = dataa.encode('utf-8')
            conn.sendall(dataa)
            conn.close()
        except KeyboardInterrupt:
            print("interrupt")
    s = setupServer()
    dataTransfer()    
def ultra():
    host=''
    port=8334
    L_PWM=0
    R_PWM=0
    L_DIR=0
    R_DIR=0

    pulse_start = 0
    pulse_end = 0
    wpi.wiringPiSetup()

    L_PWM_PIN=1  #PWM0 GPIO 1 12th pin
    R_PWM_PIN=0  #PWM1 GPIO 0 35th pin
    L_DIR_PIN=7  #     GPIO 7
    R_DIR_PIN=2  #    GPIO 2
                 #39 gnd, 6 gnd
    TRIG1_PIN=4   #    GPIO 4
    ECHO1_PIN=5   #    GPIO 5
    ECHO2_PIN=6
    ECHO3_PIN=25
    ECHO4_PIN=27

    #PIN MODES 0-Input 1-Output
    wpi.pinMode(TRIG1_PIN,1)
    wpi.pinMode(ECHO1_PIN,0)
    wpi.pinMode(ECHO2_PIN,0)
    wpi.pinMode(ECHO3_PIN,0)
    wpi.pinMode(ECHO4_PIN,0)

    wpi.pinMode(L_PWM_PIN,1)
    wpi.pinMode(R_PWM_PIN,1)
    wpi.pinMode(L_DIR_PIN,1)
    wpi.pinMode(R_DIR_PIN,1)

    #PWM Initialisations
    wpi.softPwmCreate(L_PWM_PIN,0,100)
    wpi.softPwmCreate(R_PWM_PIN,0,100)

    #Init
    wpi.softPwmWrite(L_PWM_PIN,0)
    wpi.softPwmWrite(R_PWM_PIN,0)
    wpi.digitalWrite(L_DIR_PIN,0)
    wpi.digitalWrite(R_DIR_PIN,0)


    def finddistance(TRIG,ECHO):
        wpi.digitalWrite(TRIG,0)
        time.sleep(0.000002)
        wpi.digitalWrite(TRIG,1)
        time.sleep(0.00001)
        wpi.digitalWrite(TRIG,0)
        pulse_start = time.time()
        pulse_end = time.time()
        while wpi.digitalRead(ECHO)==0:
            pulse_start = time.time()
        while wpi.digitalRead(ECHO)==1:
            pulse_end = time.time()
        pulse_duration = pulse_end-pulse_start
        fdistance = (pulse_duration * 34300)/2
        time.sleep(0.2)
        return fdistance
    def dis(d):
        if d==1:
           fdistance = finddistance(TRIG1_PIN,ECHO1_PIN)
           print("f distance : ",fdistance,"cm")
           if(fdistance<70):
            return 'w'
           else:
            return 'l'
        elif d==2:
           bdistance = finddistance(TRIG1_PIN,ECHO2_PIN)
           print("b distance : ",bdistance,"cm")
           if(bdistance<70):
            return 'x'
           else:
            return 'm'
        elif d==3:
           rdistance = finddistance(TRIG1_PIN,ECHO3_PIN)
           print("r distance : ",rdistance,"cm")
           if(rdistance<70):
            return 'y'
           else:
            return 'n'
        elif d==4:
           ldistance = finddistance(TRIG1_PIN,ECHO4_PIN)
           print("l distance : ",ldistance,"cm")
           if(ldistance<70):
            return  'z'
           else:
            return 'o'
    def setupServer():
        s = socket(AF_INET,SOCK_STREAM)
        print("Socket Created")
        try:
            s.bind((host,port))
            s.listen(1)
        except socket.error as msg:
            print(msg)
        return s
    def dataTransfer():
      num=1  
      while True:
        if num ==5:
           num=1 
        try: 
         conn,address = s.accept()
        except:
         print('nothing')
        try :
         data = dis(num)
         data = data.encode('utf-8')
         conn.sendall(data)
         num=num+1
        except:
         print('error')    
    s =setupServer()
    dataTransfer()
def checking_mot1():
    host=''
    port=8234
    L_PWM=0
    R_PWM=0
    L_DIR=0
    R_DIR=0
    wpi.wiringPiSetup()

    ##PINS
    L_PWM_PIN=1 #PWM0 GPIO 1 12th pin   
    R_PWM_PIN=0 #PWM1 GPIO 0 35th pin 
    L_DIR_PIN=7 #     GPIO 7                
    R_DIR_PIN=2  #    GPIO 2               

    #PIN MODES 0-Input 1-Output
    wpi.pinMode(L_PWM_PIN,1)
    wpi.pinMode(R_PWM_PIN,1)
    wpi.pinMode(L_DIR_PIN,1)
    wpi.pinMode(R_DIR_PIN,1)

    #PWM Initialisations
    wpi.softPwmCreate(L_PWM_PIN,0,100)
    wpi.softPwmCreate(R_PWM_PIN,0,100)

    #Init
    wpi.softPwmWrite(L_PWM_PIN,0)
    wpi.softPwmWrite(R_PWM_PIN,0)
    wpi.digitalWrite(L_DIR_PIN,0)
    wpi.digitalWrite(R_DIR_PIN,0)


    def do_GET(Resquest):
            if Resquest == 'f':
                            wpi.softPwmWrite(R_PWM_PIN,0)
                            wpi.softPwmWrite(L_PWM_PIN,0)
                            wpi.digitalWrite(L_DIR_PIN,1)
                            wpi.digitalWrite(R_DIR_PIN,0)
                            wpi.softPwmWrite(L_PWM_PIN,75)
                            wpi.softPwmWrite(R_PWM_PIN,75)
                            print( "Direction Forward")
            if Resquest == 'b':
                            wpi.softPwmWrite(R_PWM_PIN,0)
                            wpi.softPwmWrite(L_PWM_PIN,0)
                            wpi.digitalWrite(L_DIR_PIN,0)
                            wpi.digitalWrite(R_DIR_PIN,1)
                            wpi.softPwmWrite(L_PWM_PIN,75)
                            wpi.softPwmWrite(R_PWM_PIN,75)
                            print( "Direction Reverse")
            if Resquest == 'r':
                            wpi.softPwmWrite(R_PWM_PIN,0)
                            wpi.softPwmWrite(L_PWM_PIN,0)
                            wpi.digitalWrite(L_DIR_PIN,0)
                            wpi.digitalWrite(R_DIR_PIN,0)
                            wpi.softPwmWrite(L_PWM_PIN,75)
                            wpi.softPwmWrite(R_PWM_PIN,75)
                            print( "Direction Right")
            if Resquest == 'l':
                            wpi.softPwmWrite(R_PWM_PIN,0)
                            wpi.softPwmWrite(L_PWM_PIN,0)
                            wpi.digitalWrite(L_DIR_PIN,1)
                            wpi.digitalWrite(R_DIR_PIN,1)
                            wpi.softPwmWrite(R_PWM_PIN,75)
                            wpi.softPwmWrite(L_PWM_PIN,75)
                            print( "Direction Left")
            if Resquest == 's':
                            wpi.softPwmWrite(R_PWM_PIN,0)
                            wpi.softPwmWrite(L_PWM_PIN,0)
                            print( "Stop")
    def setupServer():
        s = socket(AF_INET,SOCK_STREAM)
        print("Socket Created")
        try:
            s.bind((host,port))
            s.listen(1)
        except socket.error as msg:
            print(msg)
        return s

    def dataTransfer(prev):
      while True:
        try: 
         conn,address = s.accept()
        except:
         print('nothing')
        try :
          while True:
            data = conn.recv(1024)
            data = data.decode('utf-8')
            if not data:
                break
            elif prev!=data:
                if prev=='s':
                   do_GET(data)
                   print(data)
                elif data=='s':
                   print(data)
                   do_GET(data)
            prev=data
        except KeyboardInterrupt:
            print("interrupt")
        conn.close()
    s = setupServer()
    dataTransfer('s')
def one(): ultra()
def two(): checking_mot1()
def three():connection()
Thread(target=one).start()
Thread(target=two).start()
Thread(target=three).start()

            
