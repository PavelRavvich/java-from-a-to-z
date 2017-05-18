package ru.pravvich.parser;

import java.sql.Timestamp;

/**
 * Describe Proposal post.
 */
public class Proposal {
    /**
     * Header of Proposal.
     */
    private String header;
    /**
     * Author of Proposal.
     */
    private String author;
    /**
     * Create date of Proposal.
     */
    private Timestamp create;
    /**
     * Link to Proposal page.
     */
    private String ulrPropose;
    /**
     * Account recruiter.
     */
    private String urlRecruiter;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getNickname() {
        return author;
    }

    public void setNickname(String author) {
        this.author = author;
    }

    public Timestamp getCreateTime() {
        return create;
    }

    public void setCreate(Timestamp create) {
        this.create = create;
    }

    public String getUlrPropose() {
        return ulrPropose;
    }

    public void setUlrPropose(String ulrPropose) {
        this.ulrPropose = ulrPropose;
    }

    public String getUrlRecruiter() {
        return urlRecruiter;
    }

    public void setUrlRecruiter(String urlRecruiter) {
        this.urlRecruiter = urlRecruiter;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "header='" + header + '\'' +
                ", author='" + author + '\'' +
                ", create=" + create +
                ", ulrPropose='" + ulrPropose + '\'' +
                ", urlRecruiter='" + urlRecruiter + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Proposal proposal = (Proposal) o;

        if (!header.equals(proposal.header)) return false;
        if (!author.equals(proposal.author)) return false;
        if (!create.equals(proposal.create)) return false;
        if (!ulrPropose.equals(proposal.ulrPropose)) return false;
        return urlRecruiter.equals(proposal.urlRecruiter);

    }

    @Override
    public int hashCode() {
        int result = header.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + create.hashCode();
        result = 31 * result + ulrPropose.hashCode();
        result = 31 * result + urlRecruiter.hashCode();
        return result;
    }
}
