package github.shardul.cats.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Shardul on 29/03/18.
 */

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface CatsApplicationScope {
}
