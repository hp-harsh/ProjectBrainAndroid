package hp.harsh.projectbrain.modules;

import dagger.Module;
import dagger.Provides;
import hp.harsh.projectbrain.annotations.ApiServiceScope;
import hp.harsh.projectbrain.util.ResourceUtil;
import hp.harsh.projectbrain.util.RxBus;


@Module()
public class UtilModule {

    @ApiServiceScope
    @Provides
    public ResourceUtil resourceUtil() {
        return new ResourceUtil();
    }

    @ApiServiceScope
    @Provides
    public RxBus rxBus() {
        return new RxBus();
    }
}
