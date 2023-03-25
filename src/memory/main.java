package memory;

import arc.util.CommandHandler;
import arc.util.Log;

import com.sun.management.HotSpotDiagnosticMXBean;
import mindustry.mod.Mod;

import java.io.IOException;
import java.lang.management.ManagementFactory;

public class main extends Mod {
    public String save() throws IOException {
        String file="/snapshot/hprof/"+System.currentTimeMillis()+".hprof";
        HotSpotDiagnosticMXBean bean=ManagementFactory.getPlatformMXBean(HotSpotDiagnosticMXBean.class);
        bean.dumpHeap(file,true);
        return file;
    }
    @Override
    public void init() {

    }

    @Override
    public void registerServerCommands(CommandHandler handler) {
        Log.info("Memory Loaded!");
        handler.register("memory","","Dump Heap",args->{
            try {
                Log.info("Save to:{0}",save());
            } catch (IOException e) {
                Log.err("Save Error:"+ e);
            }
        });
    }
}
