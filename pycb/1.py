import io
def czzlaji():
    try:
        file = open("wls.txt", encoding="utf8")
        lines = file.readlines()
        return lines
        
    finally:
        if file:
            file.close()
def sort(lines):
    with io.open('output.txt', "w", encoding="utf-8") as out:
        for i in lines:
            i = i[:-2]
            if '"' in i:
                out.write("\n"+i)
            else:
                try:
                    m = i.find(",")
                    if i[m+1] == " ":
                       out.write("\n"+i)
                    else:
                        out.write(i)
                except:
                    out.write(i)
        out.close()
            
    
sort(czzlaji())
    
    
