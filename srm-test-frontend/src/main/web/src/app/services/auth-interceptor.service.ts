import {  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError  } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        req = req.clone({
          setHeaders: {
            'X-Requested-With' : 'XMLHttpRequest'
          },
        });

    return next.handle(req).pipe(
        catchError((error: HttpErrorResponse) => {
          let errorMessage = '';
          if (error.error instanceof ErrorEvent) {
            errorMessage = `Error: ${error.error.message}`;
          } else {
            if(error.status != 401 && error.status != 403){
              errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
            } else {
              alert('Invalid Credentials');
            }
          }

          if(errorMessage != ''){
            console.log(errorMessage);
          }

          return throwError(errorMessage);
        })
      );
  }
}
