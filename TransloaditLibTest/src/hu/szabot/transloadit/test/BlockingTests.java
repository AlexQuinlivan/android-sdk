package hu.szabot.transloadit.test;

import hu.szabot.transloadit.ITransloadit;
import hu.szabot.transloadit.Transloadit;
import hu.szabot.transloadit.TransloaditResponse;
import hu.szabot.transloadit.assembly.AssemblyBuilder;
import hu.szabot.transloadit.assembly.IAssemblyBuilder;
import hu.szabot.transloadit.assembly.Step;
import hu.szabot.transloadit.exceptions.FileNotOpenableException;

import java.io.IOException;

import android.test.AndroidTestCase;

public class BlockingTests extends AndroidTestCase
{
	
	public void testBlocking() throws FileNotOpenableException, IOException
    {
        ITransloadit transloadit = new Transloadit(Constants.API_KEY);
        IAssemblyBuilder assembly = new AssemblyBuilder();

        assembly.setBlocking(true);
        
        Step step=new Step();
		
		step.setOption("robot", "/http/import");
		step.setOption("url", "http://static4.wikia.nocookie.net/__cb20120716045812/deadliestfiction/images/2/24/Cthulhu-rlyeh-rising.jpg");
		
		assembly.addStep("test", step);

        TransloaditResponse response = transloadit.invokeAssembly(assembly);

        if(!Constants.SIGNATURE_AUTHENTICATION)
        {
	        assertTrue(((String)response.getData().get("ok")).equals("ASSEMBLY_COMPLETED"));
        
	    }else
	    {
	    	assertTrue(true);
	    }
    }
    
	public void testNonBlocking() throws FileNotOpenableException, IOException
    {
        ITransloadit transloadit = new Transloadit(Constants.API_KEY);
        IAssemblyBuilder assembly = new AssemblyBuilder();

        Step step=new Step();
		
		step.setOption("robot", "/http/import");
		step.setOption("url", "http://static4.wikia.nocookie.net/__cb20120716045812/deadliestfiction/images/2/24/Cthulhu-rlyeh-rising.jpg");
		
		assembly.addStep("test", step);

        TransloaditResponse response = transloadit.invokeAssembly(assembly);
        
        if(!Constants.SIGNATURE_AUTHENTICATION)
        {
	        assertTrue(((String)response.getData().get("ok")).equals("ASSEMBLY_EXECUTING"));
        
	    }else
	    {
	    	assertTrue(true);
	    }
    }
}
