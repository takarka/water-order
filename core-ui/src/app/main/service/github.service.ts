import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild, Injectable } from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import { merge, Observable, of as observableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';

export interface GithubApi {
    items: GithubIssue[];
    total_count: number;
}

export interface GithubIssue {
    created_at: string;
    number: string;
    state: string;
    title: string;
}

@Injectable({
    providedIn: 'root'
})
export class GithubHttpService {
    constructor(private http: HttpClient) {}

    getRepoIssues(
        sort: string,
        order: string,
        page: number
    ): Observable<GithubApi> {
        const href = 'https://api.github.com/search/issues';
        const requestUrl = `${href}?q=repo:angular/material2&sort=${sort}&order=${order}&page=${page + 1}&size=10`;

        console.log(requestUrl);
        return this.http.get<GithubApi>(requestUrl);
    }
}
