package com.rslakra.melody.ews.artist.converter;

/**
 * This is a type converter for Artist objects.
 */
public class ArtistTypeConverter /* implements TypeConverter<Artist>*/ {

//    @Override
//    public void setLocale(Locale locale) {
//    }
//
//    /**
//     * Takes the passed identifier and tries to look up a corresponding Artist in the datastore and return it.
//     *
//     * @param artistIdentifier
//     * @param targetType
//     * @param errors
//     * @return
//     */
//    @Override
//    public Artist convert(String artistIdentifier,
//                          Class<? extends Artist> targetType,
//                          Collection<ValidationErrors> errors) {
//        // If no identifier is passed, just return null.
//        if (artistIdentifier == null) {
//            return null;
//        }
//
//        // First, try to retrieve the artist by name
//        Artist artist = Datastore.getArtistByName(artistIdentifier);
//
//        // If artist is null then try to look up by ID
//        if (artist == null) {
//            try {
//                artist = Datastore.getArtistById(Long.parseLong(artistIdentifier));
//            } catch (NumberFormatException nfe) {
//                // We don't care about this.  It is the same result.
//            }
//
//            // If the artist is not found by the TypeConverter, then create a
//            // ResourceNotFoundError which will be passed back to the caller
//            if (artist == null) {
//                errors.add(
//                    new ResourceNotFoundError("Unable to find artist by identifier passed => " + artistIdentifier));
//            }
//        }
//
//        return artist;
//    }
}
